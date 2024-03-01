package model;

import java.util.Objects;
import java.util.regex.Pattern;

public record TokenType(Terminal terminal, Pattern pattern) {
    public static TokenType END_TYPE = new TokenType(Terminal.END, Pattern.compile(Terminal.END.value()));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenType tokenType = (TokenType) o;
        return Objects.equals(terminal, tokenType.terminal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terminal);
    }
}
