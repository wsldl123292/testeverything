// Generated from F:/GitHub/testeverything/antlr/src/main/java/esql\SQLLexer.g4 by ANTLR 4.5.3
package esql;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, FROM=2, WHERE=3, AND=4, OR=5, IS=6, NULL=7, LIKE=8, IN=9, EXISTS=10, 
		ALL=11, TRUE=12, FALSE=13, BETWEEN=14, REGEXP=15, LIMIT=16, NEGATION=17, 
		VERTBAR=18, RPAREN=19, LPAREN=20, RBRACK=21, LBRACK=22, COLON=23, ALL_FIELDS=24, 
		EQ=25, LTH=26, GTH=27, NOT_EQ=28, NOT=29, LET=30, GET=31, SEMI=32, COMMA=33, 
		DOT=34, ORDER=35, GROUP=36, BY=37, ID=38, INT=39, NEWLINE=40, WS=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SELECT", "FROM", "WHERE", "AND", "OR", "IS", "NULL", "LIKE", "IN", "EXISTS", 
		"ALL", "TRUE", "FALSE", "BETWEEN", "REGEXP", "LIMIT", "NEGATION", "VERTBAR", 
		"RPAREN", "LPAREN", "RBRACK", "LBRACK", "COLON", "ALL_FIELDS", "EQ", "LTH", 
		"GTH", "NOT_EQ", "NOT", "LET", "GET", "SEMI", "COMMA", "DOT", "ORDER", 
		"GROUP", "BY", "ID", "INT", "NEWLINE", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "'~'", "'|'", "')'", "'('", "']'", "'['", 
		"':'", "'*'", "'='", "'<'", "'>'", "'!='", "'not'", "'<='", "'>='", "';'", 
		"','", "'.'", "'order'", "'group'", "'by'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SELECT", "FROM", "WHERE", "AND", "OR", "IS", "NULL", "LIKE", "IN", 
		"EXISTS", "ALL", "TRUE", "FALSE", "BETWEEN", "REGEXP", "LIMIT", "NEGATION", 
		"VERTBAR", "RPAREN", "LPAREN", "RBRACK", "LBRACK", "COLON", "ALL_FIELDS", 
		"EQ", "LTH", "GTH", "NOT_EQ", "NOT", "LET", "GET", "SEMI", "COMMA", "DOT", 
		"ORDER", "GROUP", "BY", "ID", "INT", "NEWLINE", "WS"
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


	public SQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQLLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0151\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2b\n\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3l\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4x\n\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u0082\n\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6\u008a\n\6\3\7\3\7\3\7\3\7\5\7\u0090\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\b\u009a\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a4\n\t\3\n"+
		"\3\n\3\n\3\n\5\n\u00aa\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\5\13\u00b8\n\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00c0\n\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ca\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00d6\n\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00e6\n\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00f4\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0100\n\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3 \3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3"+
		"%\3&\3&\3&\3\'\6\'\u013b\n\'\r\'\16\'\u013c\3(\6(\u0140\n(\r(\16(\u0141"+
		"\3)\5)\u0145\n)\3)\3)\3)\3)\3*\6*\u014c\n*\r*\16*\u014d\3*\3*\2\2+\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+\3\2\4\b\2//\62;C\\aac|\u4e02\u9fa7\5\2\13\f\17"+
		"\17\"\"\u0166\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\3a\3\2\2\2\5k\3\2\2\2\7w\3\2\2\2\t\u0081\3\2\2\2\13\u0089\3"+
		"\2\2\2\r\u008f\3\2\2\2\17\u0099\3\2\2\2\21\u00a3\3\2\2\2\23\u00a9\3\2"+
		"\2\2\25\u00b7\3\2\2\2\27\u00bf\3\2\2\2\31\u00c9\3\2\2\2\33\u00d5\3\2\2"+
		"\2\35\u00e5\3\2\2\2\37\u00f3\3\2\2\2!\u00ff\3\2\2\2#\u0101\3\2\2\2%\u0103"+
		"\3\2\2\2\'\u0105\3\2\2\2)\u0107\3\2\2\2+\u0109\3\2\2\2-\u010b\3\2\2\2"+
		"/\u010d\3\2\2\2\61\u010f\3\2\2\2\63\u0111\3\2\2\2\65\u0113\3\2\2\2\67"+
		"\u0115\3\2\2\29\u0117\3\2\2\2;\u011a\3\2\2\2=\u011e\3\2\2\2?\u0121\3\2"+
		"\2\2A\u0124\3\2\2\2C\u0126\3\2\2\2E\u0128\3\2\2\2G\u012a\3\2\2\2I\u0130"+
		"\3\2\2\2K\u0136\3\2\2\2M\u013a\3\2\2\2O\u013f\3\2\2\2Q\u0144\3\2\2\2S"+
		"\u014b\3\2\2\2UV\7u\2\2VW\7g\2\2WX\7n\2\2XY\7g\2\2YZ\7e\2\2Zb\7v\2\2["+
		"\\\7U\2\2\\]\7G\2\2]^\7N\2\2^_\7G\2\2_`\7E\2\2`b\7V\2\2aU\3\2\2\2a[\3"+
		"\2\2\2b\4\3\2\2\2cd\7h\2\2de\7t\2\2ef\7q\2\2fl\7o\2\2gh\7H\2\2hi\7T\2"+
		"\2ij\7Q\2\2jl\7O\2\2kc\3\2\2\2kg\3\2\2\2l\6\3\2\2\2mn\7y\2\2no\7j\2\2"+
		"op\7g\2\2pq\7t\2\2qx\7g\2\2rs\7Y\2\2st\7J\2\2tu\7G\2\2uv\7T\2\2vx\7G\2"+
		"\2wm\3\2\2\2wr\3\2\2\2x\b\3\2\2\2yz\7c\2\2z{\7p\2\2{\u0082\7f\2\2|}\7"+
		"(\2\2}\u0082\7(\2\2~\177\7C\2\2\177\u0080\7P\2\2\u0080\u0082\7F\2\2\u0081"+
		"y\3\2\2\2\u0081|\3\2\2\2\u0081~\3\2\2\2\u0082\n\3\2\2\2\u0083\u0084\7"+
		"q\2\2\u0084\u008a\7t\2\2\u0085\u0086\7~\2\2\u0086\u008a\7~\2\2\u0087\u0088"+
		"\7Q\2\2\u0088\u008a\7T\2\2\u0089\u0083\3\2\2\2\u0089\u0085\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\f\3\2\2\2\u008b\u008c\7k\2\2\u008c\u0090\7u\2\2\u008d"+
		"\u008e\7K\2\2\u008e\u0090\7U\2\2\u008f\u008b\3\2\2\2\u008f\u008d\3\2\2"+
		"\2\u0090\16\3\2\2\2\u0091\u0092\7p\2\2\u0092\u0093\7w\2\2\u0093\u0094"+
		"\7n\2\2\u0094\u009a\7n\2\2\u0095\u0096\7P\2\2\u0096\u0097\7W\2\2\u0097"+
		"\u0098\7N\2\2\u0098\u009a\7N\2\2\u0099\u0091\3\2\2\2\u0099\u0095\3\2\2"+
		"\2\u009a\20\3\2\2\2\u009b\u009c\7n\2\2\u009c\u009d\7k\2\2\u009d\u009e"+
		"\7m\2\2\u009e\u00a4\7g\2\2\u009f\u00a0\7N\2\2\u00a0\u00a1\7K\2\2\u00a1"+
		"\u00a2\7M\2\2\u00a2\u00a4\7G\2\2\u00a3\u009b\3\2\2\2\u00a3\u009f\3\2\2"+
		"\2\u00a4\22\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00aa\7p\2\2\u00a7\u00a8"+
		"\7K\2\2\u00a8\u00aa\7P\2\2\u00a9\u00a5\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\24\3\2\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7z\2\2\u00ad\u00ae\7k\2\2\u00ae"+
		"\u00af\7u\2\2\u00af\u00b0\7v\2\2\u00b0\u00b8\7u\2\2\u00b1\u00b2\7G\2\2"+
		"\u00b2\u00b3\7Z\2\2\u00b3\u00b4\7K\2\2\u00b4\u00b5\7U\2\2\u00b5\u00b6"+
		"\7V\2\2\u00b6\u00b8\7U\2\2\u00b7\u00ab\3\2\2\2\u00b7\u00b1\3\2\2\2\u00b8"+
		"\26\3\2\2\2\u00b9\u00ba\7c\2\2\u00ba\u00bb\7n\2\2\u00bb\u00c0\7n\2\2\u00bc"+
		"\u00bd\7C\2\2\u00bd\u00be\7N\2\2\u00be\u00c0\7N\2\2\u00bf\u00b9\3\2\2"+
		"\2\u00bf\u00bc\3\2\2\2\u00c0\30\3\2\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3"+
		"\7t\2\2\u00c3\u00c4\7w\2\2\u00c4\u00ca\7g\2\2\u00c5\u00c6\7V\2\2\u00c6"+
		"\u00c7\7T\2\2\u00c7\u00c8\7W\2\2\u00c8\u00ca\7G\2\2\u00c9\u00c1\3\2\2"+
		"\2\u00c9\u00c5\3\2\2\2\u00ca\32\3\2\2\2\u00cb\u00cc\7h\2\2\u00cc\u00cd"+
		"\7c\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d6\7g\2\2\u00d0"+
		"\u00d1\7H\2\2\u00d1\u00d2\7C\2\2\u00d2\u00d3\7N\2\2\u00d3\u00d4\7U\2\2"+
		"\u00d4\u00d6\7G\2\2\u00d5\u00cb\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d6\34\3"+
		"\2\2\2\u00d7\u00d8\7d\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7v\2\2\u00da"+
		"\u00db\7y\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7g\2\2\u00dd\u00e6\7p\2\2"+
		"\u00de\u00df\7D\2\2\u00df\u00e0\7G\2\2\u00e0\u00e1\7V\2\2\u00e1\u00e2"+
		"\7Y\2\2\u00e2\u00e3\7G\2\2\u00e3\u00e4\7G\2\2\u00e4\u00e6\7P\2\2\u00e5"+
		"\u00d7\3\2\2\2\u00e5\u00de\3\2\2\2\u00e6\36\3\2\2\2\u00e7\u00e8\7t\2\2"+
		"\u00e8\u00e9\7g\2\2\u00e9\u00ea\7i\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec"+
		"\7z\2\2\u00ec\u00f4\7r\2\2\u00ed\u00ee\7T\2\2\u00ee\u00ef\7G\2\2\u00ef"+
		"\u00f0\7I\2\2\u00f0\u00f1\7G\2\2\u00f1\u00f2\7Z\2\2\u00f2\u00f4\7R\2\2"+
		"\u00f3\u00e7\3\2\2\2\u00f3\u00ed\3\2\2\2\u00f4 \3\2\2\2\u00f5\u00f6\7"+
		"n\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7o\2\2\u00f8\u00f9\7k\2\2\u00f9\u0100"+
		"\7v\2\2\u00fa\u00fb\7N\2\2\u00fb\u00fc\7K\2\2\u00fc\u00fd\7O\2\2\u00fd"+
		"\u00fe\7K\2\2\u00fe\u0100\7V\2\2\u00ff\u00f5\3\2\2\2\u00ff\u00fa\3\2\2"+
		"\2\u0100\"\3\2\2\2\u0101\u0102\7\u0080\2\2\u0102$\3\2\2\2\u0103\u0104"+
		"\7~\2\2\u0104&\3\2\2\2\u0105\u0106\7+\2\2\u0106(\3\2\2\2\u0107\u0108\7"+
		"*\2\2\u0108*\3\2\2\2\u0109\u010a\7_\2\2\u010a,\3\2\2\2\u010b\u010c\7]"+
		"\2\2\u010c.\3\2\2\2\u010d\u010e\7<\2\2\u010e\60\3\2\2\2\u010f\u0110\7"+
		",\2\2\u0110\62\3\2\2\2\u0111\u0112\7?\2\2\u0112\64\3\2\2\2\u0113\u0114"+
		"\7>\2\2\u0114\66\3\2\2\2\u0115\u0116\7@\2\2\u01168\3\2\2\2\u0117\u0118"+
		"\7#\2\2\u0118\u0119\7?\2\2\u0119:\3\2\2\2\u011a\u011b\7p\2\2\u011b\u011c"+
		"\7q\2\2\u011c\u011d\7v\2\2\u011d<\3\2\2\2\u011e\u011f\7>\2\2\u011f\u0120"+
		"\7?\2\2\u0120>\3\2\2\2\u0121\u0122\7@\2\2\u0122\u0123\7?\2\2\u0123@\3"+
		"\2\2\2\u0124\u0125\7=\2\2\u0125B\3\2\2\2\u0126\u0127\7.\2\2\u0127D\3\2"+
		"\2\2\u0128\u0129\7\60\2\2\u0129F\3\2\2\2\u012a\u012b\7q\2\2\u012b\u012c"+
		"\7t\2\2\u012c\u012d\7f\2\2\u012d\u012e\7g\2\2\u012e\u012f\7t\2\2\u012f"+
		"H\3\2\2\2\u0130\u0131\7i\2\2\u0131\u0132\7t\2\2\u0132\u0133\7q\2\2\u0133"+
		"\u0134\7w\2\2\u0134\u0135\7r\2\2\u0135J\3\2\2\2\u0136\u0137\7d\2\2\u0137"+
		"\u0138\7{\2\2\u0138L\3\2\2\2\u0139\u013b\t\2\2\2\u013a\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013dN\3\2\2\2"+
		"\u013e\u0140\4\62;\2\u013f\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u013f"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142P\3\2\2\2\u0143\u0145\7\17\2\2\u0144"+
		"\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7\f"+
		"\2\2\u0147\u0148\3\2\2\2\u0148\u0149\b)\2\2\u0149R\3\2\2\2\u014a\u014c"+
		"\t\3\2\2\u014b\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014b\3\2\2\2\u014d"+
		"\u014e\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\b*\2\2\u0150T\3\2\2\2\27"+
		"\2akw\u0081\u0089\u008f\u0099\u00a3\u00a9\u00b7\u00bf\u00c9\u00d5\u00e5"+
		"\u00f3\u00ff\u013c\u0141\u0144\u014d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}