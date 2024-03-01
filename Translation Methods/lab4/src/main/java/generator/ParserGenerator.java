package generator;

import model.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ParserGenerator {
    private static final String TAB = "\t";
    private static final String PARSE_METHOD_NAME_PATTERN = "parse%s(%s)";
    private static final String HEADER = """
            package parser;
            
            import lexer.LexicalAnalyzer;
            import model.*;""";

    private final String expectMethod;

    private final Grammar grammar;

    public ParserGenerator(Grammar grammar) {
        this.grammar = grammar;
        expectMethod = """
                private void expect(String token) {
                        if (!token.equals(lex.curToken().tokenType().terminal().value())) {
                            throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
                        }
                    }""";
    }

    public String generateParser() {
        return HEADER
                + System.lineSeparator()
                + generateClassDeclaration()
                + System.lineSeparator()
                + TAB
                + expectMethod
                + System.lineSeparator()
                + generateParserMethods()
                + System.lineSeparator()
                + generateNodeClasses()
                + System.lineSeparator()
                + "}";
    }

    private String generateNodeClasses() {
        StringBuilder res = new StringBuilder();
        Map<String, List<String>> synthesizedArgs = grammar.getSynthesizedArgs();
        for (Map.Entry<String, List<String>> entry : synthesizedArgs.entrySet()) {
            res.append(generateNodeClass(entry.getKey(), entry.getValue())).append(System.lineSeparator());
        }
        return res.toString();
    }

    private String generateNodeClass(String nonTerm, List<String> values) {
        StringBuilder res = new StringBuilder();
        res.append(TAB)
                .append("public static class %s extends Tree {".formatted(nonTerm))
                .append(System.lineSeparator());

        for (String val : values) {
            res.append(TAB.repeat(2)).append("public ").append(val).append(" = 0;").append(System.lineSeparator());
        }
        res.append(TAB.repeat(2))
                .append("public %s(String name) {".formatted(nonTerm))
                .append(System.lineSeparator());
        res.append(TAB.repeat(3)).append("super(name);").append(System.lineSeparator());
        res.append(TAB.repeat(2)).append("}").append(System.lineSeparator());
        res.append(TAB).append("}").append(System.lineSeparator());
        return res.toString();
    }

    private String generateClassDeclaration() {
        String classDeclarationTemplate = """
                public class %s {
                    private static final String UNEXPECTED_TOKEN_MESSAGE = "Unexpected token at pos=";
                    private final Grammar grammar;
                    private LexicalAnalyzer lex;
                    
                    public %s(Grammar grammar) {
                        this.grammar = grammar;
                    }
                    
                    public %s parse(CharSequence cs) {
                        lex = new LexicalAnalyzer(cs, grammar);
                        lex.nextToken();
                        if (lex.curToken().tokenType().terminal().equals(Terminal.END)) {
                            return null;
                        }
                        %s parseResult = %s;
                        expect(Terminal.END.value());
                        return parseResult;
                    }
                    """;
        return classDeclarationTemplate
                .formatted(grammar.getName(), grammar.getName(), grammar.getParserRules().get(0).leftPart().name(),  grammar.getParserRules().get(0).leftPart().name(), PARSE_METHOD_NAME_PATTERN.formatted(
                        grammar.getParserRules().get(0).leftPart().name(),
                        String.join(", ", grammar.getInheritedArgs().get("E"))
                        ));
    }

    private String generateParserMethods() {
        Set<NonTerminal> nonTerminals = grammar.getParserRules().stream().map(ParserRule::leftPart)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        StringBuilder sb = new StringBuilder();
        for (NonTerminal nonTerminal : nonTerminals) {
            sb.append(TAB).append(generateSingleParseMethod(nonTerminal));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private String generateSingleParseMethod(NonTerminal nonTerminal) {
        StringBuilder res = new StringBuilder();
        res.append("private %s ".formatted(nonTerminal.name()))
                .append(PARSE_METHOD_NAME_PATTERN.formatted(
                        nonTerminal.name(), String.join(", ", grammar.getInheritedArgs().get(nonTerminal.name()))))
                .append(" {")
                .append(System.lineSeparator());
        res.append(TAB.repeat(2))
                .append("%s res = new %s(\"".formatted(nonTerminal.name(), nonTerminal.name()))
                .append(nonTerminal.name()).append("\");")
                .append(System.lineSeparator());
        res.append(TAB.repeat(2))
                .append("Token token = lex.curToken();")
                .append(System.lineSeparator());
        res.append(TAB.repeat(2))
                .append("switch (token.tokenType().terminal().value()) {")
                .append(System.lineSeparator());
        List<ParserRule> nonTermRules = getParserRulesByNonTerminal(nonTerminal);
        for (ParserRule nonTermRule : nonTermRules) {
            if (!nonTermRule.rightPart().get(0).equals(Terminal.EPS)) {
                res.append(generateCase(nonTermRule));
            }
        }
        if (grammar.getFirst().get(nonTerminal).contains(Terminal.EPS)) {
            Set<Terminal> follow = grammar.getFollow().get(nonTerminal);
            res.append(TAB.repeat(3)).append("case ")
                    .append(follow.stream().map(t -> "\"" + t.value() + "\"").collect(Collectors.joining(", ")))
                    .append(" -> {")
                    .append(System.lineSeparator());
            ParserRule epsRule = nonTermRules.stream().filter(r -> r.rightPart().get(0).equals(Terminal.EPS)).findFirst().get();
            String afterCode = epsRule.afterCode();
            res.append(TAB.repeat(4))
                    .append(afterCode.length() > 1 ? afterCode.substring(1, afterCode.length() - 1) : "")
                    .append(System.lineSeparator());
            res.append(TAB.repeat(4)).append("res.addChild(new Tree(Terminal.EPS.value()));")
                    .append(System.lineSeparator())
                    .append(TAB.repeat(3)).append("}")
                    .append(System.lineSeparator());
        }
        res.append(TAB.repeat(3))
                .append("default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());")
                .append(System.lineSeparator());
        res.append(TAB.repeat(2)).append("}").append(System.lineSeparator());
        res.append(TAB.repeat(2)).append("return res;").append(System.lineSeparator());
        res.append(TAB).append("}").append(System.lineSeparator());
        return res.toString();
    }

    private String generateCase(ParserRule nonTermRule) {
        List<Node> rightPartNodes = nonTermRule.rightPart();
        Set<Terminal> firstByRule = grammar.getFirst().get(rightPartNodes.get(0));
        StringBuilder res = new StringBuilder();
        res.append(TAB.repeat(3)).append("case ")
                .append(firstByRule.stream()
                        .filter(t -> !t.equals(Terminal.EPS))
                        .map(t -> "\"" + t.value() + "\"")
                        .collect(Collectors.joining(", ")))
                .append(" -> {")
                .append(System.lineSeparator());

        for (Node rightPartNode : rightPartNodes) {
            if (rightPartNode instanceof Terminal) {
                res.append(TAB.repeat(4)).append("expect(\"%s\");".formatted(((Terminal) rightPartNode).value())).append(System.lineSeparator());
                res.append(TAB.repeat(4)).append("res.addChild(new Tree(lex.curToken().value()));").append(System.lineSeparator());
                res.append(TAB.repeat(4)).append("lex.nextToken();").append(System.lineSeparator());
            } else {
                String argsCode = ((NonTerminal) rightPartNode).argsCode();
                res.append(TAB.repeat(4))
                        .append("res.addChild(")
                        .append(PARSE_METHOD_NAME_PATTERN.formatted(
                                ((NonTerminal) rightPartNode).name(),
                                argsCode.length() > 1 ? argsCode.substring(1, argsCode.length() - 1) : ""))
                        .append(");")
                        .append(System.lineSeparator());
            }
        }

        String afterCode = nonTermRule.afterCode();
        res.append(TAB.repeat(4))
                .append(afterCode.length() > 1 ? afterCode.substring(1, afterCode.length() - 1) : "")
                .append(System.lineSeparator());
        res.append(TAB.repeat(3))
                .append("}")
                .append(System.lineSeparator());

        return res.toString();
    }

    private List<ParserRule> getParserRulesByNonTerminal(NonTerminal nonTerminal) {
        return grammar.getParserRules().stream().filter(r -> r.leftPart().equals(nonTerminal)).toList();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
