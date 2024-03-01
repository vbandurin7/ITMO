package model;

import java.util.*;

public class Grammar {
    private final Map<Node, Set<Terminal>> first = new HashMap<>();
    private final Map<Node, Set<Terminal>> follow = new HashMap<>();
    private final Map<String, List<String>> inheritedArgs;
    private final Map<String, List<String>> synthesizedArgs;
    private final List<LexerRule> lexerRules;
    private final List<ParserRule> parserRules;

    private final String name;

    public Grammar(String name, List<ParserRule> parserRules, List<LexerRule> lexerRules,
                   Map<String, List<String>> inheritedArgs, Map<String, List<String>> synthesizedArgs) {
        this.name = name;
        this.lexerRules = lexerRules;
        this.parserRules = parserRules;
        this.inheritedArgs = inheritedArgs;
        this.synthesizedArgs = synthesizedArgs;
        for (ParserRule parserRule : parserRules) {
            first.put(parserRule.leftPart(), new LinkedHashSet<>());
            follow.put(parserRule.leftPart(), new LinkedHashSet<>());
        }
        lexerRules.forEach(r -> first.put(r.leftPart(), new LinkedHashSet<>(Set.of(r.leftPart()))));
        follow.get(parserRules.get(0).leftPart()).add(Terminal.END);
        initFirst();
        initFollow();
    }

    private void initFirst() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (ParserRule parserRule : parserRules) {
                List<Node> rightPartNodes = parserRule.rightPart();
                for (int i = 0; i < rightPartNodes.size(); i++) {
                    Node node = rightPartNodes.get(i);
                    if (node instanceof NonTerminal nonTerm && !first.get(nonTerm).contains(Terminal.EPS)) {
                        changed |= first.get(parserRule.leftPart()).addAll(first.get(nonTerm));
                        break;
                    } else if (i == 0 && node instanceof Terminal term) {
                        changed |= first.get(parserRule.leftPart()).add(term);
                        break;
                    }
                }
            }
        }
    }

    private void initFollow() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (ParserRule rule : parserRules) {
                NonTerminal leftPart = rule.leftPart();
                List<Node> rightPart = rule.rightPart();
                for (int j = 0; j < rightPart.size() - 1; j++) {
                    Node node = rightPart.get(j);
                    if (node instanceof Terminal) {
                        continue;
                    }
                    Node nextNode = rightPart.get(j + 1);
                    changed |= follow.get(node).addAll(first.get(nextNode).stream()
                            .filter(t -> !t.equals(Terminal.EPS)).toList());
                    if (first.get(nextNode).contains(Terminal.EPS)) {
                        changed |= follow.get(node).addAll(follow.get(leftPart));
                    }
                }
                Node lastNode = rightPart.get(rightPart.size() - 1);
                if (lastNode instanceof NonTerminal) {
                    changed |= follow.get(lastNode).addAll(follow.get(leftPart));
                }
            }
        }
    }

    public List<LexerRule> getLexerRules() {
        return lexerRules;
    }

    public List<ParserRule> getParserRules() {
        return parserRules;
    }

    public Map<String, List<String>> getInheritedArgs() {
        return inheritedArgs;
    }

    public Map<String, List<String>> getSynthesizedArgs() {
        return synthesizedArgs;
    }

    public Map<Node, Set<Terminal>> getFirst() {
        return first;
    }

    public Map<Node, Set<Terminal>> getFollow() {
        return follow;
    }

    public String getName() {
        return name;
    }
}
