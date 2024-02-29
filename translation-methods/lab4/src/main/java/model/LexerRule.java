package model;

public record LexerRule(Terminal leftPart, String rightPart) implements Rule {
}
