// Generated from F:/GitHub/testeverything/antlr/src/main/java\Calc.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WS=8, ID=9, NUMBER=10, 
		ADD=11, SUB=12, MUL=13, DIV=14;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "WS", "ID", "NUMBER", 
		"ADD", "SUB", "MUL", "DIV"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'set'", "';'", "'='", "'calc'", "'('", "')'", "','", null, null, 
		null, "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "WS", "ID", "NUMBER", 
		"ADD", "SUB", "MUL", "DIV"
	};
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


	public CalcLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Calc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\20U\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\6\t\64\n\t\r\t\16\t"+
		"\65\3\t\3\t\3\n\6\n;\n\n\r\n\16\n<\3\13\6\13@\n\13\r\13\16\13A\3\13\3"+
		"\13\6\13F\n\13\r\13\16\13G\5\13J\n\13\5\13L\n\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\2\2\20\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\3\2\5\5\2\13\f\17\17\"\"\3\2c|\3\2\62;Z\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\3\37\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3"+
		"\2\2\2\13,\3\2\2\2\r.\3\2\2\2\17\60\3\2\2\2\21\63\3\2\2\2\23:\3\2\2\2"+
		"\25?\3\2\2\2\27M\3\2\2\2\31O\3\2\2\2\33Q\3\2\2\2\35S\3\2\2\2\37 \7u\2"+
		"\2 !\7g\2\2!\"\7v\2\2\"\4\3\2\2\2#$\7=\2\2$\6\3\2\2\2%&\7?\2\2&\b\3\2"+
		"\2\2\'(\7e\2\2()\7c\2\2)*\7n\2\2*+\7e\2\2+\n\3\2\2\2,-\7*\2\2-\f\3\2\2"+
		"\2./\7+\2\2/\16\3\2\2\2\60\61\7.\2\2\61\20\3\2\2\2\62\64\t\2\2\2\63\62"+
		"\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\b"+
		"\t\2\28\22\3\2\2\29;\t\3\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2="+
		"\24\3\2\2\2>@\t\4\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BK\3\2\2"+
		"\2CI\7\60\2\2DF\t\4\2\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2"+
		"\2\2IE\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KC\3\2\2\2KL\3\2\2\2L\26\3\2\2\2MN\7"+
		"-\2\2N\30\3\2\2\2OP\7/\2\2P\32\3\2\2\2QR\7,\2\2R\34\3\2\2\2ST\7\61\2\2"+
		"T\36\3\2\2\2\t\2\65<AGIK\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}