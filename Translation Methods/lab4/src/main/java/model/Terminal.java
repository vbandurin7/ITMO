package model;

import java.util.Objects;

public record Terminal(String value) implements Node {
    public static Terminal END = new Terminal("$");
    public static final Terminal EPS = new Terminal("eps");

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        return Objects.equals(value, terminal.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
