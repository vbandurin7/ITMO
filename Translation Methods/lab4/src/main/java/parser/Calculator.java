package parser;

import lexer.LexicalAnalyzer;
import model.*;
public class Calculator {
    private static final String UNEXPECTED_TOKEN_MESSAGE = "Unexpected token at pos=";
    private final Grammar grammar;
    private LexicalAnalyzer lex;

    public Calculator(Grammar grammar) {
        this.grammar = grammar;
    }

    public E parse(CharSequence cs) {
        lex = new LexicalAnalyzer(cs, grammar);
        lex.nextToken();
        if (lex.curToken().tokenType().terminal().equals(Terminal.END)) {
            return null;
        }
        E parseResult = parseE();
        expect(Terminal.END.value());
        return parseResult;
    }

	private void expect(String token) {
        if (!token.equals(lex.curToken().tokenType().terminal().value())) {
            throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
        }
    }
	private E parseE() {
		E res = new E("E");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "sub", "n", "lparen" -> {
				res.addChild(parseT());
				res.addChild(parseEP(((T) res.getChildren().get("T")).val));
				res.val = ((EP) res.getChildren().get("EP")).val;
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private EP parseEP(double acc) {
		EP res = new EP("EP");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "add" -> {
				expect("add");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseT());
				res.addChild(parseEP(acc + ((T) res.getChildren().get("T")).val));
				res.val = ((EP) res.getChildren().get("EP")).val;
			}
			case "sub" -> {
				expect("sub");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseT());
				res.addChild(parseEP(acc - ((T) res.getChildren().get("T")).val));
				res.val = ((EP) res.getChildren().get("EP")).val;
			}
			case "$", "rparen" -> {
				res.val = acc;
				res.addChild(new Tree(Terminal.EPS.value()));
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private T parseT() {
		T res = new T("T");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "sub", "n", "lparen" -> {
				res.addChild(parseP());
				res.addChild(parseTP(((P) res.getChildren().get("P")).val));
				res.val = ((TP) res.getChildren().get("TP")).val;
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private TP parseTP(double acc) {
		TP res = new TP("TP");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "mul" -> {
				expect("mul");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseP());
				res.addChild(parseTP(acc * ((P) res.getChildren().get("P")).val));
				res.val = ((TP) res.getChildren().get("TP")).val;
			}
			case "div" -> {
				expect("div");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseP());
				res.addChild(parseTP(acc / ((P) res.getChildren().get("P")).val));
				res.val = ((TP) res.getChildren().get("TP")).val;
			}
			case "add", "sub", "$", "rparen" -> {
				res.val = acc;
				res.addChild(new Tree(Terminal.EPS.value()));
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private P parseP() {
		P res = new P("P");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "sub", "n", "lparen" -> {
				res.addChild(parseF());
				res.addChild(parsePP(((F) res.getChildren().get("F")).val));
				res.val = ((PP) res.getChildren().get("PP")).val;
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private PP parsePP(double acc) {
		PP res = new PP("PP");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "pow" -> {
				expect("pow");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseP());
				res.val = Math.pow(acc, ((P) res.getChildren().get("P")).val);
			}
			case "mul", "div", "add", "sub", "$", "rparen" -> {
				res.val = acc;
				res.addChild(new Tree(Terminal.EPS.value()));
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private F parseF() {
		F res = new F("F");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "sub" -> {
				expect("sub");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseF());
				res.val = -1 * ((F) res.getChildren().get("F")).val;
			}
			case "n" -> {
				expect("n");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.val = Double.parseDouble(token.value());
			}
			case "lparen" -> {
				expect("lparen");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseE());
				expect("rparen");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.val = ((E) res.getChildren().get("E")).val;
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}


	public static class P extends Tree {
		public double val = 0;
		public P(String name) {
			super(name);
		}
	}

	public static class PP extends Tree {
		public double val = 0;
		public PP(String name) {
			super(name);
		}
	}

	public static class T extends Tree {
		public double val = 0;
		public T(String name) {
			super(name);
		}
	}

	public static class E extends Tree {
		public double val = 0;
		public E(String name) {
			super(name);
		}
	}

	public static class F extends Tree {
		public double val = 0;
		public F(String name) {
			super(name);
		}
	}

	public static class EP extends Tree {
		public double val = 0;
		public EP(String name) {
			super(name);
		}
	}

	public static class TP extends Tree {
		public double val = 0;
		public TP(String name) {
			super(name);
		}
	}


}