package model;

import java.util.Objects;

public record Token(TokenType tokenType, String value) {
    public static Token END_TOKEN = new Token(TokenType.END_TYPE, Terminal.END.value());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(tokenType, token.tokenType);
    }
}
