public enum Token {
    LPAREN("("),
    RPAREN(")"),
    VAR("var"),
    IMPLICATION("=>"),
    AND("and"),
    OR("or"),
    XOR("xor"),
    IN("in"),
    NOT("not"),
    END("-1");

    private String val;

    Token(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
