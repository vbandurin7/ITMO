import java.util.*;

public class Obfuscator {
    private final static String TAB = "\t";
    private final Map<String, String> variables = new HashMap<>();
    private final Set<String> obfuscatedVariables = new HashSet<>();
    private final List<String> alphabet = List.of("I", "O", "1", "0");
    private final List<String> types = List.of("int", "long", "double", "boolean");
    private final List<String> possibleArithmeticOperations = List.of("+", "-", "/", "*");
    private final List<String> possibleBooleanOperations = List.of(">", "<", ">=", "<=", "==");
    private final List<String> possibleStatements = List.of("if", "while");
    private final Random randomizer = new Random();
    private int varNameLength = 5;

    public String obfuscateVariable(String name) {
        variables.putIfAbsent(name, obfuscate());
        return variables.get(name);
    }

    private String obfuscate() {
        StringBuilder res = new StringBuilder();
        do {
            res.append(getRandomStart());
            for (int i = 0; i < varNameLength(); i++) {
                res.append(getRandomSymbol());
            }
        } while (!obfuscatedVariables.add(res.toString()));

        return res.toString();
    }

    private int varNameLength() {
        int curCount = (int) (2 * Math.pow(4, varNameLength - 1)); // сколько можем закодировать переменных сейчас
        if (curCount - variables.size() <= 256) { // если в запасе меньше, чем 256 слотов, инкрементим
            return ++varNameLength;
        }
        return varNameLength;
    }

    private String getRandomStart() {
        return alphabet.get(randomizer.nextInt(2));
    }

    public String generateUselessExpression(int indent) {
        String statement = getPossibleStatement();
        return switch (statement) {
            case "if" -> generateIfExpression(indent);
            case "while" -> generateWhileExpression(indent);
            default -> "";
        };
    }

    private String generateWhileExpression(int indent) {
        StringBuilder res = new StringBuilder();
        String uselessVariableName = getUselessVariableName();
        res.append(System.lineSeparator()).append(TAB.repeat(indent));
        res.append("int ").append(uselessVariableName).append("=").append(randomizer.nextInt()).append(";");
        res.append(System.lineSeparator()).append(TAB.repeat(indent));
        res.append("while(").append(uselessVariableName).append("<").append(randomizer.nextInt()).append(") {");
        res.append(generateExpressionBody(indent + 1));
        res.append(System.lineSeparator()).append(TAB.repeat(indent + 1));
        res.append(uselessVariableName).append("=").append(uselessVariableName).append("+").append("1;");
        res.append(System.lineSeparator()).append(TAB.repeat(indent));
        res.append("}");
        return res.toString();
    }

    private String generateIfExpression(int indent) {
        StringBuilder res = new StringBuilder();
        String randomType = getRandomType();
        res.append("if (");
        if (randomType.equals("boolean")) {
            res.append("true").append(") {");
            res.append(generateExpressionBody(indent + 1));
            res.append(System.lineSeparator()).append(TAB.repeat(indent));
            res.append("}");
        } else {
            res.append(generateIfCondition())
                    .append(") {")
                    .append(generateExpressionBody(indent + 1))
                    .append(System.lineSeparator())
                    .append(TAB.repeat(indent))
                    .append("}");
        }
        return res.toString();
    }

    private String generateIfCondition() {
        return randomizer.nextInt() + getPossibleBooleanOperation() + randomizer.nextInt();
    }

    private String getUselessVariableName() {
        StringBuilder uselessVarName;
        do {
            uselessVarName = new StringBuilder(getRandomStart());
            for (int i = 0; i < varNameLength(); i++) {
                uselessVarName.append(getRandomSymbol());
            }
        } while (obfuscatedVariables.contains(uselessVarName.toString()));
        return uselessVarName.toString();
    }

    private String generateExpressionBody(int indent) {
        StringBuilder res = new StringBuilder();
        String type = getRandomNumericType();
        String uselessVariableName = getUselessVariableName();
        res.append(System.lineSeparator()).append(TAB.repeat(indent))
                .append(type)
                .append(" ")
                .append(uselessVariableName)
                .append(" = (")
                .append(type)
                .append(")")
                .append(randomizer.nextInt())
                .append(";")
                .append(System.lineSeparator())
                .append(TAB.repeat(indent))
                .append(uselessVariableName)
                .append("=")
                .append(uselessVariableName)
                .append(getPossibleOperation())
                .append(randomizer.nextInt(0, Integer.MAX_VALUE))
                .append(";");
        return res.toString();
    }

    private String getRandomSymbol() {
        return alphabet.get(randomizer.nextInt(alphabet.size()));
    }

    private String getRandomType() {
        return types.get(randomizer.nextInt(types.size()));
    }

    private String getRandomNumericType() {
        return types.get(randomizer.nextInt(types.size() - 1));
    }

    private String getPossibleOperation() {
        return possibleArithmeticOperations.get(randomizer.nextInt(possibleArithmeticOperations.size()));
    }

    private String getPossibleBooleanOperation() {
        return possibleBooleanOperations.get(randomizer.nextInt(possibleBooleanOperations.size()));
    }
    private String getPossibleStatement() {
        return possibleStatements.get(randomizer.nextInt(possibleStatements.size()));
    }
}
