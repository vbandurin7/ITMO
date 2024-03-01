import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private static final Map<String, Token> OPERATION_TOKEN_MAP =
            Map.of("not", Token.NOT,
                    "in", Token.IN,
                    "and", Token.AND,
                    "or", Token.OR,
                    "xor", Token.XOR,
                    "=>", Token.IMPLICATION
            );
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("[a-z]");

    private final InputStream is;
    private int curChar;
    private int curPos;
    private Token curToken;

    public LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    public void nextToken() throws ParseException {
        while (isBlank(curChar)) {
            nextChar();
        }
        switch (curChar) {
            case '(' -> {
                nextChar();
                curToken = Token.LPAREN;
            }
            case ')' -> {
                nextChar();
                curToken = Token.RPAREN;
            }
            case -1 -> curToken = Token.END;
            default -> {
                String token = getNextToken();
                if (OPERATION_TOKEN_MAP.containsKey(token)) {
                    curToken = OPERATION_TOKEN_MAP.get(token);
                } else if (VARIABLE_PATTERN.matcher(token).matches()) {
                    curToken = Token.VAR;
                    curToken.setVal(token);
                } else {
                    throw new ParseException("Illegal character " + token, curPos - token.length());
                }
            }
        }
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c);
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    private String getNextToken() throws ParseException {

        StringBuilder sb = new StringBuilder(curChar);
        while (!isBlank(curChar) && curChar != -1 &&
                (VARIABLE_PATTERN.matcher(Character.toString(curChar)).matches() || curChar == '=' || curChar == '>')) {
            sb.append(Character.toString(curChar));
            nextChar();
        }
        return sb.toString();
    }

    public int getCurPos() {
        return curPos;
    }

    public Token curToken() {
        return curToken;
    }
}
