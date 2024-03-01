package model;

import java.util.List;

public record ParserRule(NonTerminal leftPart, List<Node> rightPart, String afterCode) implements Rule {
}
