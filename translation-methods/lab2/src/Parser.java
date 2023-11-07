import java.io.InputStream;
import java.text.ParseException;


public class Parser {
    private static final String UNEXPECTED_TOKEN_MESSAGE = "Unexpected token %s at pos %d";
    private LexicalAnalyzer lex;


    public Tree parse(InputStream is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        if (lex.curToken() == Token.END) {
            return null;
        }
        Tree parseResult = parseI();
        expect(Token.END);
        return parseResult;
    }

    private Tree parseI() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case LPAREN, VAR, NOT -> {
                return new Tree("I", parseE(), parseIPrime());
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseE() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case LPAREN, VAR, NOT -> {
                return new Tree("E", parseT(), parseEPrime());
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseT() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case LPAREN, NOT, VAR -> {
                return new Tree("T", parseF(), parseTPrime());
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseIPrime() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case IMPLICATION -> {
                nextToken();
                return new Tree("I'", new Tree("=>"), parseI());
            }
            case END, RPAREN -> {
                return new Tree("I'", new Tree("eps"));
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseTPrime() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case AND -> {
                nextToken();
                return new Tree("T'", new Tree("and"), parseF(), parseTPrime());
            }
            case XOR, OR, END, RPAREN, IMPLICATION -> {
                return new Tree("T'", new Tree("eps"));
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseEPrime() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case OR, XOR -> {
                nextToken();
                return new Tree("E'", new Tree(token.getVal()), parseT(), parseEPrime());
            }
            case END, RPAREN, IMPLICATION -> {
                return new Tree("E'", new Tree("eps"));
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseF() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case NOT -> {
                nextToken();
                return new Tree("F", new Tree(token.getVal()), parseF());
            }
            case VAR -> {
                nextToken();
                return new Tree("F", new Tree(token.getVal()), parseU());
            }
            case LPAREN -> {
                nextToken();
                Tree sub = parseI();
                expect(Token.RPAREN);
                Tree res = new Tree("F", new Tree("("), sub, new Tree(")"));
                nextToken();
                return res;
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private Tree parseU() throws ParseException {
        Token token = lex.curToken();
        switch (token) {
            case NOT -> {
                nextToken();
                return new Tree("U", new Tree(token.getVal()), parseU());
            }
            case IN -> {
                nextToken();
                expect(Token.VAR);
                Token varToken = lex.curToken();
                nextToken();
                return new Tree("U", new Tree("in"), new Tree(varToken.getVal()));
            }
            default -> throw unexpectedTokenException(token);
        }
    }

    private void nextToken() throws ParseException {
        lex.nextToken();
    }

    private ParseException unexpectedTokenException(Token token) {
        int exceptionPos = lex.getCurPos() - token.getVal().length() - 1;
        return new ParseException(UNEXPECTED_TOKEN_MESSAGE.formatted(token, exceptionPos), exceptionPos);
    }

    private void expect(Token token) throws ParseException {
        if (token != lex.curToken()) {
            throw unexpectedTokenException(lex.curToken());
        }
    }
}
