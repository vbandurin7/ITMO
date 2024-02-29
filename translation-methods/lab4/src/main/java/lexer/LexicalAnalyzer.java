package lexer;

import model.Grammar;
import model.Token;
import model.TokenType;

import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private final Map<TokenType, Matcher> tokenTypes;
    private final CharSequence input;
    private final Grammar grammar;
    private final int length;
    private int curPos;
    private Token curToken;

    public LexicalAnalyzer(CharSequence input, Grammar grammar) {
        tokenTypes = new LinkedHashMap<>();
        this.grammar = grammar;
        grammar.getLexerRules()
                .forEach(lr -> {
                    TokenType tokenType = new TokenType(lr.leftPart(), Pattern.compile(lr.rightPart()));
                    tokenTypes.put(tokenType, tokenType.pattern().matcher(input));
                });
        this.input = input;
        curPos = 0;
        length = input.length();
    }

    public void nextToken() {
        if (curPos == length) {
            curToken = Token.END_TOKEN;
            return;
        }
        for (Map.Entry<TokenType, Matcher> tokenTypeMatcherEntry : tokenTypes.entrySet()) {
            Matcher matcher = tokenTypeMatcherEntry.getValue();
            if (matcher.region(curPos, length).lookingAt()) {
                int end = matcher.end();
                if (tokenTypeMatcherEntry.getKey().terminal().value().equals("whitespace")) {
                    curPos = end;
                    nextToken();
                    return;
                }
                curToken = new Token(tokenTypeMatcherEntry.getKey(), input.subSequence(curPos, end).toString());
                curPos = end;
                return;
            }
        }
        throw new RuntimeException("Unexpected token starts from pos=" + curPos);
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c);
    }

    public int getCurPos() {
        return curPos;
    }

    public Token curToken() {
        return curToken;
    }

    public Grammar getGrammar() {
        return grammar;
    }
}