package parser;

import lexer.LexicalAnalyzer;
import model.*;
public class Lab2Parser {
    private static final String UNEXPECTED_TOKEN_MESSAGE = "Unexpected token at pos=";
    private final Grammar grammar;
    private LexicalAnalyzer lex;

    public Lab2Parser(Grammar grammar) {
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
			case "not", "var", "lparen" -> {
				res.addChild(parseT());
				res.addChild(parseEP());
				
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private EP parseEP() {
		EP res = new EP("EP");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "or" -> {
				expect("or");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseT());
				res.addChild(parseEP());
				
			}
			case "xor" -> {
				expect("xor");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseT());
				res.addChild(parseEP());
				
			}
			case "$", "rparen" -> {
				
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
			case "not", "var", "lparen" -> {
				res.addChild(parseF());
				res.addChild(parseTP());
				
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private TP parseTP() {
		TP res = new TP("TP");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "and" -> {
				expect("and");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseF());
				res.addChild(parseTP());
				
			}
			case "or", "xor", "$", "rparen" -> {
				
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
			case "not" -> {
				expect("not");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseF());
				
			}
			case "var" -> {
				expect("var");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseU());
				
			}
			case "lparen" -> {
				expect("lparen");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseE());
				expect("rparen");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}

	private U parseU() {
		U res = new U("U");
		Token token = lex.curToken();
		switch (token.tokenType().terminal().value()) {
			case "not" -> {
				expect("not");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				res.addChild(parseU());
				
			}
			case "in" -> {
				expect("in");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				expect("var");
				res.addChild(new Tree(lex.curToken().value()));
				lex.nextToken();
				
			}
			default -> throw new RuntimeException(UNEXPECTED_TOKEN_MESSAGE + lex.getCurPos());
		}
		return res;
	}


	public static class T extends Tree {
		public T(String name) {
			super(name);
		}
	}

	public static class E extends Tree {
		public E(String name) {
			super(name);
		}
	}

	public static class U extends Tree {
		public U(String name) {
			super(name);
		}
	}

	public static class F extends Tree {
		public F(String name) {
			super(name);
		}
	}

	public static class EP extends Tree {
		public EP(String name) {
			super(name);
		}
	}

	public static class TP extends Tree {
		public TP(String name) {
			super(name);
		}
	}


}