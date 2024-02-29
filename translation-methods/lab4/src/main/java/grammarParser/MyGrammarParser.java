// Generated from ./MyGrammar.g4 by ANTLR 4.13.1

package grammarParser;
import model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import java.util.Collections;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MyGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, LONG=2, DOUBLE=3, LBRACE=4, RBRACE=5, LBRACKET=6, RBRACKET=7, GRAMMAR=8, 
		SEMICOLON=9, COMMA=10, EPS=11, COLON=12, INHERITED=13, SYNTHESIZED=14, 
		ARROW=15, REGEX=16, JAVA_CODE=17, NONTERMINAL=18, TERMINAL=19, FILE_NAME=20, 
		WS=21, ENDL=22;
	public static final int
		RULE_input_grammar = 0, RULE_attributes_declaration = 1, RULE_rules = 2, 
		RULE_singleRule = 3, RULE_singleParserRule = 4, RULE_codeBlock = 5, RULE_singleLexerRule = 6, 
		RULE_type = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"input_grammar", "attributes_declaration", "rules", "singleRule", "singleParserRule", 
			"codeBlock", "singleLexerRule", "type"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'long'", "'double'", "'{'", "'}'", "'['", "']'", "'grammar'", 
			"';'", "','", "'eps'", "':'", "'inherited'", "'synthesized'", "'->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "LONG", "DOUBLE", "LBRACE", "RBRACE", "LBRACKET", "RBRACKET", 
			"GRAMMAR", "SEMICOLON", "COMMA", "EPS", "COLON", "INHERITED", "SYNTHESIZED", 
			"ARROW", "REGEX", "JAVA_CODE", "NONTERMINAL", "TERMINAL", "FILE_NAME", 
			"WS", "ENDL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MyGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Input_grammarContext extends ParserRuleContext {
		public model.Grammar grammar;
		public Token FILE_NAME;
		public Attributes_declarationContext attributes_declaration;
		public RulesContext rules;
		public TerminalNode GRAMMAR() { return getToken(MyGrammarParser.GRAMMAR, 0); }
		public TerminalNode FILE_NAME() { return getToken(MyGrammarParser.FILE_NAME, 0); }
		public TerminalNode SEMICOLON() { return getToken(MyGrammarParser.SEMICOLON, 0); }
		public Attributes_declarationContext attributes_declaration() {
			return getRuleContext(Attributes_declarationContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MyGrammarParser.EOF, 0); }
		public Input_grammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input_grammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterInput_grammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitInput_grammar(this);
		}
	}

	public final Input_grammarContext input_grammar() throws RecognitionException {
		Input_grammarContext _localctx = new Input_grammarContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_input_grammar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(GRAMMAR);
			setState(17);
			((Input_grammarContext)_localctx).FILE_NAME = match(FILE_NAME);
			setState(18);
			match(SEMICOLON);
			setState(19);
			((Input_grammarContext)_localctx).attributes_declaration = attributes_declaration();
			setState(20);
			((Input_grammarContext)_localctx).rules = rules();
			setState(21);
			match(EOF);

			    ((Input_grammarContext)_localctx).grammar =  new model.Grammar(
			        (((Input_grammarContext)_localctx).FILE_NAME!=null?((Input_grammarContext)_localctx).FILE_NAME.getText():null),
			        ((Input_grammarContext)_localctx).rules.parserRules,
			        ((Input_grammarContext)_localctx).rules.lexerRules,
			        ((Input_grammarContext)_localctx).attributes_declaration.inheritedArgs,
			        ((Input_grammarContext)_localctx).attributes_declaration.synthesizedArgs
			    );
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Attributes_declarationContext extends ParserRuleContext {
		public Map<String, List<String>> inheritedArgs;
		public Map<String, List<String>> synthesizedArgs;
		public Token NONTERMINAL;
		public TypeContext type;
		public Token TERMINAL;
		public List<TerminalNode> NONTERMINAL() { return getTokens(MyGrammarParser.NONTERMINAL); }
		public TerminalNode NONTERMINAL(int i) {
			return getToken(MyGrammarParser.NONTERMINAL, i);
		}
		public List<TerminalNode> LBRACKET() { return getTokens(MyGrammarParser.LBRACKET); }
		public TerminalNode LBRACKET(int i) {
			return getToken(MyGrammarParser.LBRACKET, i);
		}
		public List<TerminalNode> RBRACKET() { return getTokens(MyGrammarParser.RBRACKET); }
		public TerminalNode RBRACKET(int i) {
			return getToken(MyGrammarParser.RBRACKET, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(MyGrammarParser.TERMINAL); }
		public TerminalNode TERMINAL(int i) {
			return getToken(MyGrammarParser.TERMINAL, i);
		}
		public List<TerminalNode> COLON() { return getTokens(MyGrammarParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MyGrammarParser.COLON, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MyGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MyGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> INHERITED() { return getTokens(MyGrammarParser.INHERITED); }
		public TerminalNode INHERITED(int i) {
			return getToken(MyGrammarParser.INHERITED, i);
		}
		public List<TerminalNode> SYNTHESIZED() { return getTokens(MyGrammarParser.SYNTHESIZED); }
		public TerminalNode SYNTHESIZED(int i) {
			return getToken(MyGrammarParser.SYNTHESIZED, i);
		}
		public Attributes_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterAttributes_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitAttributes_declaration(this);
		}
	}

	public final Attributes_declarationContext attributes_declaration() throws RecognitionException {
		Attributes_declarationContext _localctx = new Attributes_declarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_attributes_declaration);

		    ((Attributes_declarationContext)_localctx).inheritedArgs =  new HashMap<>();
		    ((Attributes_declarationContext)_localctx).synthesizedArgs =  new HashMap<>();
		    
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(24);
					((Attributes_declarationContext)_localctx).NONTERMINAL = match(NONTERMINAL);

					    _localctx.inheritedArgs.putIfAbsent((((Attributes_declarationContext)_localctx).NONTERMINAL!=null?((Attributes_declarationContext)_localctx).NONTERMINAL.getText():null), new ArrayList<>());
					    _localctx.synthesizedArgs.putIfAbsent((((Attributes_declarationContext)_localctx).NONTERMINAL!=null?((Attributes_declarationContext)_localctx).NONTERMINAL.getText():null), new ArrayList<>());
					    
					setState(26);
					match(LBRACKET);
					setState(40);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) {
						{
						{
						setState(27);
						((Attributes_declarationContext)_localctx).type = type();
						setState(28);
						((Attributes_declarationContext)_localctx).TERMINAL = match(TERMINAL);
						setState(29);
						match(COLON);
						setState(34);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case INHERITED:
							{
							setState(30);
							match(INHERITED);

							    _localctx.inheritedArgs.get((((Attributes_declarationContext)_localctx).NONTERMINAL!=null?((Attributes_declarationContext)_localctx).NONTERMINAL.getText():null)).add((((Attributes_declarationContext)_localctx).type!=null?_input.getText(((Attributes_declarationContext)_localctx).type.start,((Attributes_declarationContext)_localctx).type.stop):null) + " " + (((Attributes_declarationContext)_localctx).TERMINAL!=null?((Attributes_declarationContext)_localctx).TERMINAL.getText():null));
							    
							}
							break;
						case SYNTHESIZED:
							{
							setState(32);
							match(SYNTHESIZED);

							    _localctx.synthesizedArgs.get((((Attributes_declarationContext)_localctx).NONTERMINAL!=null?((Attributes_declarationContext)_localctx).NONTERMINAL.getText():null)).add((((Attributes_declarationContext)_localctx).type!=null?_input.getText(((Attributes_declarationContext)_localctx).type.start,((Attributes_declarationContext)_localctx).type.stop):null) + " " + (((Attributes_declarationContext)_localctx).TERMINAL!=null?((Attributes_declarationContext)_localctx).TERMINAL.getText():null));
							    
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(36);
						match(SEMICOLON);
						}
						}
						setState(42);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(43);
					match(RBRACKET);
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RulesContext extends ParserRuleContext {
		public List<ParserRule> parserRules;
		public List<LexerRule> lexerRules;
		public SingleRuleContext singleRule;
		public List<SingleRuleContext> rls = new ArrayList<SingleRuleContext>();
		public List<SingleRuleContext> singleRule() {
			return getRuleContexts(SingleRuleContext.class);
		}
		public SingleRuleContext singleRule(int i) {
			return getRuleContext(SingleRuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitRules(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NONTERMINAL || _la==TERMINAL) {
				{
				{
				setState(49);
				((RulesContext)_localctx).singleRule = singleRule();
				((RulesContext)_localctx).rls.add(((RulesContext)_localctx).singleRule);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    ((RulesContext)_localctx).parserRules =  ((RulesContext)_localctx).rls.stream().filter(r -> r.rule_ instanceof ParserRule).map(r -> (ParserRule) r.rule_).collect(Collectors.toList());
			    ((RulesContext)_localctx).lexerRules =  ((RulesContext)_localctx).rls.stream().filter(r -> r.rule_ instanceof LexerRule).map(r -> (LexerRule) r.rule_).collect(Collectors.toList());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleRuleContext extends ParserRuleContext {
		public Rule rule_;
		public SingleParserRuleContext singleParserRule;
		public SingleLexerRuleContext singleLexerRule;
		public SingleParserRuleContext singleParserRule() {
			return getRuleContext(SingleParserRuleContext.class,0);
		}
		public SingleLexerRuleContext singleLexerRule() {
			return getRuleContext(SingleLexerRuleContext.class,0);
		}
		public SingleRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterSingleRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitSingleRule(this);
		}
	}

	public final SingleRuleContext singleRule() throws RecognitionException {
		SingleRuleContext _localctx = new SingleRuleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_singleRule);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONTERMINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				((SingleRuleContext)_localctx).singleParserRule = singleParserRule();

				        ((SingleRuleContext)_localctx).rule_ =  ((SingleRuleContext)_localctx).singleParserRule.rule_;
				    
				}
				break;
			case TERMINAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				((SingleRuleContext)_localctx).singleLexerRule = singleLexerRule();

				        ((SingleRuleContext)_localctx).rule_ =  ((SingleRuleContext)_localctx).singleLexerRule.rule_;
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleParserRuleContext extends ParserRuleContext {
		public ParserRule rule_;
		public Token NONTERMINAL;
		public CodeBlockContext codeBlock;
		public List<CodeBlockContext> argsCode = new ArrayList<CodeBlockContext>();
		public Token TERMINAL;
		public Token EPS;
		public List<CodeBlockContext> afterCode = new ArrayList<CodeBlockContext>();
		public List<TerminalNode> NONTERMINAL() { return getTokens(MyGrammarParser.NONTERMINAL); }
		public TerminalNode NONTERMINAL(int i) {
			return getToken(MyGrammarParser.NONTERMINAL, i);
		}
		public TerminalNode ARROW() { return getToken(MyGrammarParser.ARROW, 0); }
		public TerminalNode SEMICOLON() { return getToken(MyGrammarParser.SEMICOLON, 0); }
		public TerminalNode EPS() { return getToken(MyGrammarParser.EPS, 0); }
		public List<CodeBlockContext> codeBlock() {
			return getRuleContexts(CodeBlockContext.class);
		}
		public CodeBlockContext codeBlock(int i) {
			return getRuleContext(CodeBlockContext.class,i);
		}
		public List<TerminalNode> TERMINAL() { return getTokens(MyGrammarParser.TERMINAL); }
		public TerminalNode TERMINAL(int i) {
			return getToken(MyGrammarParser.TERMINAL, i);
		}
		public SingleParserRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleParserRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterSingleParserRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitSingleParserRule(this);
		}
	}

	public final SingleParserRuleContext singleParserRule() throws RecognitionException {
		SingleParserRuleContext _localctx = new SingleParserRuleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_singleParserRule);

		        NonTerminal leftPart;
		        List<Node> rightPart = new ArrayList<>();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((SingleParserRuleContext)_localctx).NONTERMINAL = match(NONTERMINAL);

			        leftPart = new NonTerminal((((SingleParserRuleContext)_localctx).NONTERMINAL!=null?((SingleParserRuleContext)_localctx).NONTERMINAL.getText():null), "");
			    
			setState(67);
			match(ARROW);
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONTERMINAL:
			case TERMINAL:
				{
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(75);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case NONTERMINAL:
						{
						setState(68);
						((SingleParserRuleContext)_localctx).NONTERMINAL = match(NONTERMINAL);
						setState(70);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==JAVA_CODE) {
							{
							setState(69);
							((SingleParserRuleContext)_localctx).codeBlock = ((SingleParserRuleContext)_localctx).codeBlock = codeBlock();
							((SingleParserRuleContext)_localctx).argsCode.add(((SingleParserRuleContext)_localctx).codeBlock);
							}
						}


						            if (!((SingleParserRuleContext)_localctx).argsCode.isEmpty()) {
						            rightPart.add(new NonTerminal((((SingleParserRuleContext)_localctx).NONTERMINAL!=null?((SingleParserRuleContext)_localctx).NONTERMINAL.getText():null), ((SingleParserRuleContext)_localctx).codeBlock.code));
						            } else {
						            rightPart.add(new NonTerminal((((SingleParserRuleContext)_localctx).NONTERMINAL!=null?((SingleParserRuleContext)_localctx).NONTERMINAL.getText():null), ""));
						            }
						            
						}
						break;
					case TERMINAL:
						{
						setState(73);
						((SingleParserRuleContext)_localctx).TERMINAL = match(TERMINAL);
						 rightPart.add(new Terminal((((SingleParserRuleContext)_localctx).TERMINAL!=null?((SingleParserRuleContext)_localctx).TERMINAL.getText():null))); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(77); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NONTERMINAL || _la==TERMINAL );
				}
				break;
			case EPS:
				{
				setState(79);
				((SingleParserRuleContext)_localctx).EPS = match(EPS);
				 rightPart.add(new Terminal((((SingleParserRuleContext)_localctx).EPS!=null?((SingleParserRuleContext)_localctx).EPS.getText():null))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(83);
			match(SEMICOLON);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JAVA_CODE) {
				{
				setState(84);
				((SingleParserRuleContext)_localctx).codeBlock = ((SingleParserRuleContext)_localctx).codeBlock = codeBlock();
				((SingleParserRuleContext)_localctx).afterCode.add(((SingleParserRuleContext)_localctx).codeBlock);
				}
			}


			    if (!((SingleParserRuleContext)_localctx).afterCode.isEmpty()) {
			        ((SingleParserRuleContext)_localctx).rule_ =  new ParserRule(leftPart, rightPart, ((SingleParserRuleContext)_localctx).codeBlock.code);
			    } else {
			        ((SingleParserRuleContext)_localctx).rule_ =  new ParserRule(leftPart, rightPart, "");
			    }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CodeBlockContext extends ParserRuleContext {
		public String code;
		public Token JAVA_CODE;
		public TerminalNode JAVA_CODE() { return getToken(MyGrammarParser.JAVA_CODE, 0); }
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterCodeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitCodeBlock(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_codeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			((CodeBlockContext)_localctx).JAVA_CODE = match(JAVA_CODE);

			    ((CodeBlockContext)_localctx).code =  (((CodeBlockContext)_localctx).JAVA_CODE!=null?((CodeBlockContext)_localctx).JAVA_CODE.getText():null);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SingleLexerRuleContext extends ParserRuleContext {
		public LexerRule rule_;
		public Token TERMINAL;
		public Token REGEX;
		public TerminalNode TERMINAL() { return getToken(MyGrammarParser.TERMINAL, 0); }
		public TerminalNode ARROW() { return getToken(MyGrammarParser.ARROW, 0); }
		public TerminalNode REGEX() { return getToken(MyGrammarParser.REGEX, 0); }
		public TerminalNode SEMICOLON() { return getToken(MyGrammarParser.SEMICOLON, 0); }
		public SingleLexerRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleLexerRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterSingleLexerRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitSingleLexerRule(this);
		}
	}

	public final SingleLexerRuleContext singleLexerRule() throws RecognitionException {
		SingleLexerRuleContext _localctx = new SingleLexerRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_singleLexerRule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((SingleLexerRuleContext)_localctx).TERMINAL = match(TERMINAL);
			setState(93);
			match(ARROW);
			setState(94);
			((SingleLexerRuleContext)_localctx).REGEX = match(REGEX);
			setState(95);
			match(SEMICOLON);

			    ((SingleLexerRuleContext)_localctx).rule_ =  new LexerRule(new Terminal((((SingleLexerRuleContext)_localctx).TERMINAL!=null?((SingleLexerRuleContext)_localctx).TERMINAL.getText():null)), (((SingleLexerRuleContext)_localctx).REGEX!=null?((SingleLexerRuleContext)_localctx).REGEX.getText():null).substring(1, (((SingleLexerRuleContext)_localctx).REGEX!=null?((SingleLexerRuleContext)_localctx).REGEX.getText():null).length() - 1));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MyGrammarParser.INT, 0); }
		public TerminalNode LONG() { return getToken(MyGrammarParser.LONG, 0); }
		public TerminalNode DOUBLE() { return getToken(MyGrammarParser.DOUBLE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0016e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001#\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001\'\b\u0001\n\u0001"+
		"\f\u0001*\t\u0001\u0001\u0001\u0005\u0001-\b\u0001\n\u0001\f\u00010\t"+
		"\u0001\u0001\u0002\u0005\u00023\b\u0002\n\u0002\f\u00026\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003@\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004G\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0004\u0004L\b\u0004\u000b\u0004\f\u0004M\u0001\u0004"+
		"\u0001\u0004\u0003\u0004R\b\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"V\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0000\u0001\u0001\u0000\u0001\u0003f\u0000\u0010\u0001\u0000"+
		"\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u00044\u0001\u0000\u0000\u0000"+
		"\u0006?\u0001\u0000\u0000\u0000\bA\u0001\u0000\u0000\u0000\nY\u0001\u0000"+
		"\u0000\u0000\f\\\u0001\u0000\u0000\u0000\u000eb\u0001\u0000\u0000\u0000"+
		"\u0010\u0011\u0005\b\u0000\u0000\u0011\u0012\u0005\u0014\u0000\u0000\u0012"+
		"\u0013\u0005\t\u0000\u0000\u0013\u0014\u0003\u0002\u0001\u0000\u0014\u0015"+
		"\u0003\u0004\u0002\u0000\u0015\u0016\u0005\u0000\u0000\u0001\u0016\u0017"+
		"\u0006\u0000\uffff\uffff\u0000\u0017\u0001\u0001\u0000\u0000\u0000\u0018"+
		"\u0019\u0005\u0012\u0000\u0000\u0019\u001a\u0006\u0001\uffff\uffff\u0000"+
		"\u001a(\u0005\u0006\u0000\u0000\u001b\u001c\u0003\u000e\u0007\u0000\u001c"+
		"\u001d\u0005\u0013\u0000\u0000\u001d\"\u0005\f\u0000\u0000\u001e\u001f"+
		"\u0005\r\u0000\u0000\u001f#\u0006\u0001\uffff\uffff\u0000 !\u0005\u000e"+
		"\u0000\u0000!#\u0006\u0001\uffff\uffff\u0000\"\u001e\u0001\u0000\u0000"+
		"\u0000\" \u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$%\u0005\t"+
		"\u0000\u0000%\'\u0001\u0000\u0000\u0000&\u001b\u0001\u0000\u0000\u0000"+
		"\'*\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)+\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000+-\u0005\u0007"+
		"\u0000\u0000,\u0018\u0001\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000"+
		".,\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/\u0003\u0001\u0000"+
		"\u0000\u00000.\u0001\u0000\u0000\u000013\u0003\u0006\u0003\u000021\u0001"+
		"\u0000\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u0000"+
		"45\u0001\u0000\u0000\u000057\u0001\u0000\u0000\u000064\u0001\u0000\u0000"+
		"\u000078\u0006\u0002\uffff\uffff\u00008\u0005\u0001\u0000\u0000\u0000"+
		"9:\u0003\b\u0004\u0000:;\u0006\u0003\uffff\uffff\u0000;@\u0001\u0000\u0000"+
		"\u0000<=\u0003\f\u0006\u0000=>\u0006\u0003\uffff\uffff\u0000>@\u0001\u0000"+
		"\u0000\u0000?9\u0001\u0000\u0000\u0000?<\u0001\u0000\u0000\u0000@\u0007"+
		"\u0001\u0000\u0000\u0000AB\u0005\u0012\u0000\u0000BC\u0006\u0004\uffff"+
		"\uffff\u0000CQ\u0005\u000f\u0000\u0000DF\u0005\u0012\u0000\u0000EG\u0003"+
		"\n\u0005\u0000FE\u0001\u0000\u0000\u0000FG\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HL\u0006\u0004\uffff\uffff\u0000IJ\u0005\u0013\u0000"+
		"\u0000JL\u0006\u0004\uffff\uffff\u0000KD\u0001\u0000\u0000\u0000KI\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000"+
		"MN\u0001\u0000\u0000\u0000NR\u0001\u0000\u0000\u0000OP\u0005\u000b\u0000"+
		"\u0000PR\u0006\u0004\uffff\uffff\u0000QK\u0001\u0000\u0000\u0000QO\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0005\t\u0000\u0000TV\u0003"+
		"\n\u0005\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001"+
		"\u0000\u0000\u0000WX\u0006\u0004\uffff\uffff\u0000X\t\u0001\u0000\u0000"+
		"\u0000YZ\u0005\u0011\u0000\u0000Z[\u0006\u0005\uffff\uffff\u0000[\u000b"+
		"\u0001\u0000\u0000\u0000\\]\u0005\u0013\u0000\u0000]^\u0005\u000f\u0000"+
		"\u0000^_\u0005\u0010\u0000\u0000_`\u0005\t\u0000\u0000`a\u0006\u0006\uffff"+
		"\uffff\u0000a\r\u0001\u0000\u0000\u0000bc\u0007\u0000\u0000\u0000c\u000f"+
		"\u0001\u0000\u0000\u0000\n\"(.4?FKMQU";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}