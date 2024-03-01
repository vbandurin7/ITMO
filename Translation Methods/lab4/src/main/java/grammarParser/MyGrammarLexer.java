// Generated from ./MyGrammar.g4 by ANTLR 4.13.1

package grammarParser;
import model.*;
import java.util.*;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.Token;
import java.util.Collections;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MyGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, LONG=2, DOUBLE=3, LBRACE=4, RBRACE=5, LBRACKET=6, RBRACKET=7, GRAMMAR=8, 
		SEMICOLON=9, COMMA=10, EPS=11, COLON=12, INHERITED=13, SYNTHESIZED=14, 
		ARROW=15, REGEX=16, JAVA_CODE=17, NONTERMINAL=18, TERMINAL=19, FILE_NAME=20, 
		WS=21, ENDL=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "LONG", "DOUBLE", "LBRACE", "RBRACE", "LBRACKET", "RBRACKET", 
			"GRAMMAR", "SEMICOLON", "COMMA", "EPS", "COLON", "INHERITED", "SYNTHESIZED", 
			"ARROW", "REGEX", "JAVA_CODE", "NONTERMINAL", "TERMINAL", "FILE_NAME", 
			"WS", "ENDL"
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


	public MyGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MyGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0016\u00a4\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0005\u000fs\b\u000f\n\u000f\f\u000fv\t\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010}\b\u0010"+
		"\n\u0010\f\u0010\u0080\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u0086\b\u0011\n\u0011\f\u0011\u0089\t\u0011\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u008d\b\u0012\n\u0012\f\u0012\u0090\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0005\u0013\u0094\b\u0013\n\u0013\f\u0013\u0097\t\u0013"+
		"\u0001\u0014\u0004\u0014\u009a\b\u0014\u000b\u0014\f\u0014\u009b\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0004\u0015\u00a1\b\u0015\u000b\u0015\f"+
		"\u0015\u00a2\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0014)\u0015+\u0016\u0001\u0000\t\u0001\u0000\"\"\u0002\u0000{{}}\u0001"+
		"\u0000AZ\u0002\u000009AZ\u0001\u0000az\u0002\u000009az\u0004\u000009A"+
		"Z__az\u0003\u0000\t\n\r\r  \u0002\u0000\n\n\r\r\u00ab\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0001-\u0001\u0000\u0000\u0000\u00031\u0001\u0000\u0000\u0000"+
		"\u00056\u0001\u0000\u0000\u0000\u0007=\u0001\u0000\u0000\u0000\t?\u0001"+
		"\u0000\u0000\u0000\u000bA\u0001\u0000\u0000\u0000\rC\u0001\u0000\u0000"+
		"\u0000\u000fE\u0001\u0000\u0000\u0000\u0011M\u0001\u0000\u0000\u0000\u0013"+
		"O\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017U\u0001"+
		"\u0000\u0000\u0000\u0019W\u0001\u0000\u0000\u0000\u001ba\u0001\u0000\u0000"+
		"\u0000\u001dm\u0001\u0000\u0000\u0000\u001fp\u0001\u0000\u0000\u0000!"+
		"y\u0001\u0000\u0000\u0000#\u0083\u0001\u0000\u0000\u0000%\u008a\u0001"+
		"\u0000\u0000\u0000\'\u0091\u0001\u0000\u0000\u0000)\u0099\u0001\u0000"+
		"\u0000\u0000+\u00a0\u0001\u0000\u0000\u0000-.\u0005i\u0000\u0000./\u0005"+
		"n\u0000\u0000/0\u0005t\u0000\u00000\u0002\u0001\u0000\u0000\u000012\u0005"+
		"l\u0000\u000023\u0005o\u0000\u000034\u0005n\u0000\u000045\u0005g\u0000"+
		"\u00005\u0004\u0001\u0000\u0000\u000067\u0005d\u0000\u000078\u0005o\u0000"+
		"\u000089\u0005u\u0000\u00009:\u0005b\u0000\u0000:;\u0005l\u0000\u0000"+
		";<\u0005e\u0000\u0000<\u0006\u0001\u0000\u0000\u0000=>\u0005{\u0000\u0000"+
		">\b\u0001\u0000\u0000\u0000?@\u0005}\u0000\u0000@\n\u0001\u0000\u0000"+
		"\u0000AB\u0005[\u0000\u0000B\f\u0001\u0000\u0000\u0000CD\u0005]\u0000"+
		"\u0000D\u000e\u0001\u0000\u0000\u0000EF\u0005g\u0000\u0000FG\u0005r\u0000"+
		"\u0000GH\u0005a\u0000\u0000HI\u0005m\u0000\u0000IJ\u0005m\u0000\u0000"+
		"JK\u0005a\u0000\u0000KL\u0005r\u0000\u0000L\u0010\u0001\u0000\u0000\u0000"+
		"MN\u0005;\u0000\u0000N\u0012\u0001\u0000\u0000\u0000OP\u0005,\u0000\u0000"+
		"P\u0014\u0001\u0000\u0000\u0000QR\u0005e\u0000\u0000RS\u0005p\u0000\u0000"+
		"ST\u0005s\u0000\u0000T\u0016\u0001\u0000\u0000\u0000UV\u0005:\u0000\u0000"+
		"V\u0018\u0001\u0000\u0000\u0000WX\u0005i\u0000\u0000XY\u0005n\u0000\u0000"+
		"YZ\u0005h\u0000\u0000Z[\u0005e\u0000\u0000[\\\u0005r\u0000\u0000\\]\u0005"+
		"i\u0000\u0000]^\u0005t\u0000\u0000^_\u0005e\u0000\u0000_`\u0005d\u0000"+
		"\u0000`\u001a\u0001\u0000\u0000\u0000ab\u0005s\u0000\u0000bc\u0005y\u0000"+
		"\u0000cd\u0005n\u0000\u0000de\u0005t\u0000\u0000ef\u0005h\u0000\u0000"+
		"fg\u0005e\u0000\u0000gh\u0005s\u0000\u0000hi\u0005i\u0000\u0000ij\u0005"+
		"z\u0000\u0000jk\u0005e\u0000\u0000kl\u0005d\u0000\u0000l\u001c\u0001\u0000"+
		"\u0000\u0000mn\u0005-\u0000\u0000no\u0005>\u0000\u0000o\u001e\u0001\u0000"+
		"\u0000\u0000pt\u0005\"\u0000\u0000qs\b\u0000\u0000\u0000rq\u0001\u0000"+
		"\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000uw\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000"+
		"wx\u0005\"\u0000\u0000x \u0001\u0000\u0000\u0000y~\u0003\u0007\u0003\u0000"+
		"z}\u0003!\u0010\u0000{}\b\u0001\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"|{\u0001\u0000\u0000\u0000}\u0080\u0001\u0000\u0000\u0000~|\u0001\u0000"+
		"\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0081\u0001\u0000\u0000"+
		"\u0000\u0080~\u0001\u0000\u0000\u0000\u0081\u0082\u0003\t\u0004\u0000"+
		"\u0082\"\u0001\u0000\u0000\u0000\u0083\u0087\u0007\u0002\u0000\u0000\u0084"+
		"\u0086\u0007\u0003\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086"+
		"\u0089\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0001\u0000\u0000\u0000\u0088$\u0001\u0000\u0000\u0000\u0089\u0087"+
		"\u0001\u0000\u0000\u0000\u008a\u008e\u0007\u0004\u0000\u0000\u008b\u008d"+
		"\u0007\u0005\u0000\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090"+
		"\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0001\u0000\u0000\u0000\u008f&\u0001\u0000\u0000\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\u0091\u0095\u0007\u0002\u0000\u0000\u0092\u0094\u0007"+
		"\u0006\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0094\u0097\u0001"+
		"\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001"+
		"\u0000\u0000\u0000\u0096(\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000"+
		"\u0000\u0000\u0098\u009a\u0007\u0007\u0000\u0000\u0099\u0098\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0006\u0014\u0000\u0000\u009e*\u0001\u0000\u0000"+
		"\u0000\u009f\u00a1\u0007\b\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3,\u0001\u0000\u0000\u0000\t"+
		"\u0000t|~\u0087\u008e\u0095\u009b\u00a2\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}