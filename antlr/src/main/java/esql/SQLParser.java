// Generated from /Users/ldl/Documents/develop/project/github/testeveryting/antlr/src/main/java/esql/SQLParser.g4 by ANTLR 4.5.3
package esql;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLParser extends Parser {
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
	public static final int
		RULE_stat = 0, RULE_select_clause = 1, RULE_table_name = 2, RULE_table_type = 3, 
		RULE_limit_case = 4, RULE_limitexpre = 5, RULE_limitNum = 6, RULE_column_name = 7, 
		RULE_column_list_clause = 8, RULE_where_clause = 9, RULE_expression = 10, 
		RULE_element = 11, RULE_right_element = 12, RULE_left_element = 13, RULE_target_element = 14, 
		RULE_relational_op = 15, RULE_expr_op = 16, RULE_between_op = 17, RULE_is_or_is_not = 18, 
		RULE_simple_expression = 19, RULE_table_references = 20;
	public static final String[] ruleNames = {
		"stat", "select_clause", "table_name", "table_type", "limit_case", "limitexpre", 
		"limitNum", "column_name", "column_list_clause", "where_clause", "expression", 
		"element", "right_element", "left_element", "target_element", "relational_op", 
		"expr_op", "between_op", "is_or_is_not", "simple_expression", "table_references"
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

	@Override
	public String getGrammarFileName() { return "SQLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatContext extends ParserRuleContext {
		public Select_clauseContext select_clause() {
			return getRuleContext(Select_clauseContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			select_clause();
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

	public static class Select_clauseContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SQLParser.SELECT, 0); }
		public Column_list_clauseContext column_list_clause() {
			return getRuleContext(Column_list_clauseContext.class,0);
		}
		public TerminalNode FROM() { return getToken(SQLParser.FROM, 0); }
		public Table_referencesContext table_references() {
			return getRuleContext(Table_referencesContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Limit_caseContext limit_case() {
			return getRuleContext(Limit_caseContext.class,0);
		}
		public Select_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterSelect_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitSelect_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitSelect_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_clauseContext select_clause() throws RecognitionException {
		Select_clauseContext _localctx = new Select_clauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(SELECT);
			setState(45);
			column_list_clause();
			setState(48);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(46);
				match(FROM);
				setState(47);
				table_references();
				}
			}

			setState(51);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(50);
				where_clause();
				}
			}

			setState(54);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(53);
				limit_case();
				}
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

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQLParser.ID, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitTable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(ID);
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

	public static class Table_typeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQLParser.ID, 0); }
		public Table_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterTable_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitTable_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitTable_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_typeContext table_type() throws RecognitionException {
		Table_typeContext _localctx = new Table_typeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_table_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ID);
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

	public static class Limit_caseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(SQLParser.LIMIT, 0); }
		public LimitexpreContext limitexpre() {
			return getRuleContext(LimitexpreContext.class,0);
		}
		public Limit_caseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit_case; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterLimit_case(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitLimit_case(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitLimit_case(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Limit_caseContext limit_case() throws RecognitionException {
		Limit_caseContext _localctx = new Limit_caseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_limit_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(LIMIT);
			setState(61);
			limitexpre();
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

	public static class LimitexpreContext extends ParserRuleContext {
		public List<LimitNumContext> limitNum() {
			return getRuleContexts(LimitNumContext.class);
		}
		public LimitNumContext limitNum(int i) {
			return getRuleContext(LimitNumContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(SQLParser.COMMA, 0); }
		public LimitexpreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitexpre; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterLimitexpre(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitLimitexpre(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitLimitexpre(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitexpreContext limitexpre() throws RecognitionException {
		LimitexpreContext _localctx = new LimitexpreContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_limitexpre);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			limitNum();
			setState(64);
			match(COMMA);
			setState(65);
			limitNum();
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

	public static class LimitNumContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SQLParser.INT, 0); }
		public LimitNumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitNum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterLimitNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitLimitNum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitLimitNum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitNumContext limitNum() throws RecognitionException {
		LimitNumContext _localctx = new LimitNumContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_limitNum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(INT);
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

	public static class Column_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQLParser.ID, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitColumn_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(ID);
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

	public static class Column_list_clauseContext extends ParserRuleContext {
		public TerminalNode ALL_FIELDS() { return getToken(SQLParser.ALL_FIELDS, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public Column_list_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_list_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterColumn_list_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitColumn_list_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitColumn_list_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_list_clauseContext column_list_clause() throws RecognitionException {
		Column_list_clauseContext _localctx = new Column_list_clauseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_column_list_clause);
		int _la;
		try {
			setState(80);
			switch (_input.LA(1)) {
			case ALL_FIELDS:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				match(ALL_FIELDS);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				column_name();
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(73);
					match(COMMA);
					setState(74);
					column_name();
					}
					}
					setState(79);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(SQLParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitWhere_clause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitWhere_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_where_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(WHERE);
			setState(83);
			expression(0);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SQLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SQLParser.RPAREN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(SQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQLParser.OR, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(86);
				simple_expression();
				}
				break;
			case 2:
				{
				setState(87);
				match(LPAREN);
				setState(88);
				simple_expression();
				setState(89);
				match(RPAREN);
				}
				break;
			case 3:
				{
				setState(91);
				match(LPAREN);
				setState(92);
				expression(0);
				setState(93);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(103);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(97);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(98);
						((ExpressionContext)_localctx).op = match(AND);
						setState(99);
						expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(100);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(101);
						((ExpressionContext)_localctx).op = match(OR);
						setState(102);
						expression(5);
						}
						break;
					}
					} 
				}
				setState(107);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQLParser.ID, 0); }
		public TerminalNode INT() { return getToken(SQLParser.INT, 0); }
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==INT) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Right_elementContext extends ParserRuleContext {
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public Right_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterRight_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitRight_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitRight_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Right_elementContext right_element() throws RecognitionException {
		Right_elementContext _localctx = new Right_elementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_right_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			element();
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

	public static class Left_elementContext extends ParserRuleContext {
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public Left_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_left_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterLeft_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitLeft_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitLeft_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Left_elementContext left_element() throws RecognitionException {
		Left_elementContext _localctx = new Left_elementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_left_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			element();
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

	public static class Target_elementContext extends ParserRuleContext {
		public ElementContext element() {
			return getRuleContext(ElementContext.class,0);
		}
		public Target_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterTarget_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitTarget_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitTarget_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Target_elementContext target_element() throws RecognitionException {
		Target_elementContext _localctx = new Target_elementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_target_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			element();
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

	public static class Relational_opContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public TerminalNode LTH() { return getToken(SQLParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(SQLParser.GTH, 0); }
		public TerminalNode NOT_EQ() { return getToken(SQLParser.NOT_EQ, 0); }
		public TerminalNode LET() { return getToken(SQLParser.LET, 0); }
		public TerminalNode GET() { return getToken(SQLParser.GET, 0); }
		public TerminalNode LIKE() { return getToken(SQLParser.LIKE, 0); }
		public Relational_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterRelational_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitRelational_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitRelational_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Relational_opContext relational_op() throws RecognitionException {
		Relational_opContext _localctx = new Relational_opContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_relational_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LIKE) | (1L << EQ) | (1L << LTH) | (1L << GTH) | (1L << NOT_EQ) | (1L << LET) | (1L << GET))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Expr_opContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(SQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQLParser.OR, 0); }
		public TerminalNode NOT() { return getToken(SQLParser.NOT, 0); }
		public Expr_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterExpr_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitExpr_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitExpr_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_opContext expr_op() throws RecognitionException {
		Expr_opContext _localctx = new Expr_opContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expr_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Between_opContext extends ParserRuleContext {
		public TerminalNode BETWEEN() { return getToken(SQLParser.BETWEEN, 0); }
		public Between_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterBetween_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitBetween_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitBetween_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Between_opContext between_op() throws RecognitionException {
		Between_opContext _localctx = new Between_opContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_between_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(BETWEEN);
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

	public static class Is_or_is_notContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(SQLParser.IS, 0); }
		public TerminalNode NOT() { return getToken(SQLParser.NOT, 0); }
		public Is_or_is_notContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_is_or_is_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterIs_or_is_not(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitIs_or_is_not(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitIs_or_is_not(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Is_or_is_notContext is_or_is_not() throws RecognitionException {
		Is_or_is_notContext _localctx = new Is_or_is_notContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_is_or_is_not);
		try {
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				match(IS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(IS);
				setState(124);
				match(NOT);
				}
				break;
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

	public static class Simple_expressionContext extends ParserRuleContext {
		public List<Left_elementContext> left_element() {
			return getRuleContexts(Left_elementContext.class);
		}
		public Left_elementContext left_element(int i) {
			return getRuleContext(Left_elementContext.class,i);
		}
		public Relational_opContext relational_op() {
			return getRuleContext(Relational_opContext.class,0);
		}
		public Right_elementContext right_element() {
			return getRuleContext(Right_elementContext.class,0);
		}
		public Target_elementContext target_element() {
			return getRuleContext(Target_elementContext.class,0);
		}
		public Between_opContext between_op() {
			return getRuleContext(Between_opContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SQLParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(SQLParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(SQLParser.RPAREN, 0); }
		public Is_or_is_notContext is_or_is_not() {
			return getRuleContext(Is_or_is_notContext.class,0);
		}
		public TerminalNode NULL() { return getToken(SQLParser.NULL, 0); }
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterSimple_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitSimple_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitSimple_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_simple_expression);
		try {
			setState(143);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				left_element();
				setState(128);
				relational_op();
				setState(129);
				right_element();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				target_element();
				setState(132);
				between_op();
				setState(133);
				match(LPAREN);
				setState(134);
				left_element();
				setState(135);
				match(COMMA);
				setState(136);
				left_element();
				setState(137);
				match(RPAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				target_element();
				setState(140);
				is_or_is_not();
				setState(141);
				match(NULL);
				}
				break;
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

	public static class Table_referencesContext extends ParserRuleContext {
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(SQLParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SQLParser.DOT, i);
		}
		public List<Table_typeContext> table_type() {
			return getRuleContexts(Table_typeContext.class);
		}
		public Table_typeContext table_type(int i) {
			return getRuleContext(Table_typeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQLParser.COMMA, i);
		}
		public Table_referencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_references; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).enterTable_references(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLParserListener ) ((SQLParserListener)listener).exitTable_references(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLParserVisitor ) return ((SQLParserVisitor<? extends T>)visitor).visitTable_references(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_referencesContext table_references() throws RecognitionException {
		Table_referencesContext _localctx = new Table_referencesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_table_references);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			table_name();
			setState(146);
			match(DOT);
			setState(147);
			table_type();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				{
				setState(148);
				match(COMMA);
				setState(149);
				table_name();
				setState(150);
				match(DOT);
				setState(151);
				table_type();
				}
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 10:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\u00a1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\3\3\3\5\3\63"+
		"\n\3\3\3\5\3\66\n\3\3\3\5\39\n\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\7\nN\n\n\f\n\16\nQ\13\n\5\nS\n"+
		"\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fb\n\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\7\fj\n\f\f\f\16\fm\13\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\5\24\u0080"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u0092\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\7\26\u009c\n\26\f\26\16\26\u009f\13\26\3\26\2\3\26\27\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2()\5\2\n\n\33\36 !\4\2\6\7\37\37"+
		"\u0098\2,\3\2\2\2\4.\3\2\2\2\6:\3\2\2\2\b<\3\2\2\2\n>\3\2\2\2\fA\3\2\2"+
		"\2\16E\3\2\2\2\20G\3\2\2\2\22R\3\2\2\2\24T\3\2\2\2\26a\3\2\2\2\30n\3\2"+
		"\2\2\32p\3\2\2\2\34r\3\2\2\2\36t\3\2\2\2 v\3\2\2\2\"x\3\2\2\2$z\3\2\2"+
		"\2&\177\3\2\2\2(\u0091\3\2\2\2*\u0093\3\2\2\2,-\5\4\3\2-\3\3\2\2\2./\7"+
		"\3\2\2/\62\5\22\n\2\60\61\7\4\2\2\61\63\5*\26\2\62\60\3\2\2\2\62\63\3"+
		"\2\2\2\63\65\3\2\2\2\64\66\5\24\13\2\65\64\3\2\2\2\65\66\3\2\2\2\668\3"+
		"\2\2\2\679\5\n\6\28\67\3\2\2\289\3\2\2\29\5\3\2\2\2:;\7(\2\2;\7\3\2\2"+
		"\2<=\7(\2\2=\t\3\2\2\2>?\7\22\2\2?@\5\f\7\2@\13\3\2\2\2AB\5\16\b\2BC\7"+
		"#\2\2CD\5\16\b\2D\r\3\2\2\2EF\7)\2\2F\17\3\2\2\2GH\7(\2\2H\21\3\2\2\2"+
		"IS\7\32\2\2JO\5\20\t\2KL\7#\2\2LN\5\20\t\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2"+
		"\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RI\3\2\2\2RJ\3\2\2\2S\23\3\2\2\2TU\7\5"+
		"\2\2UV\5\26\f\2V\25\3\2\2\2WX\b\f\1\2Xb\5(\25\2YZ\7\26\2\2Z[\5(\25\2["+
		"\\\7\25\2\2\\b\3\2\2\2]^\7\26\2\2^_\5\26\f\2_`\7\25\2\2`b\3\2\2\2aW\3"+
		"\2\2\2aY\3\2\2\2a]\3\2\2\2bk\3\2\2\2cd\f\7\2\2de\7\6\2\2ej\5\26\f\bfg"+
		"\f\6\2\2gh\7\7\2\2hj\5\26\f\7ic\3\2\2\2if\3\2\2\2jm\3\2\2\2ki\3\2\2\2"+
		"kl\3\2\2\2l\27\3\2\2\2mk\3\2\2\2no\t\2\2\2o\31\3\2\2\2pq\5\30\r\2q\33"+
		"\3\2\2\2rs\5\30\r\2s\35\3\2\2\2tu\5\30\r\2u\37\3\2\2\2vw\t\3\2\2w!\3\2"+
		"\2\2xy\t\4\2\2y#\3\2\2\2z{\7\20\2\2{%\3\2\2\2|\u0080\7\b\2\2}~\7\b\2\2"+
		"~\u0080\7\37\2\2\177|\3\2\2\2\177}\3\2\2\2\u0080\'\3\2\2\2\u0081\u0082"+
		"\5\34\17\2\u0082\u0083\5 \21\2\u0083\u0084\5\32\16\2\u0084\u0092\3\2\2"+
		"\2\u0085\u0086\5\36\20\2\u0086\u0087\5$\23\2\u0087\u0088\7\26\2\2\u0088"+
		"\u0089\5\34\17\2\u0089\u008a\7#\2\2\u008a\u008b\5\34\17\2\u008b\u008c"+
		"\7\25\2\2\u008c\u0092\3\2\2\2\u008d\u008e\5\36\20\2\u008e\u008f\5&\24"+
		"\2\u008f\u0090\7\t\2\2\u0090\u0092\3\2\2\2\u0091\u0081\3\2\2\2\u0091\u0085"+
		"\3\2\2\2\u0091\u008d\3\2\2\2\u0092)\3\2\2\2\u0093\u0094\5\6\4\2\u0094"+
		"\u0095\7$\2\2\u0095\u009d\5\b\5\2\u0096\u0097\7#\2\2\u0097\u0098\5\6\4"+
		"\2\u0098\u0099\7$\2\2\u0099\u009a\5\b\5\2\u009a\u009c\3\2\2\2\u009b\u0096"+
		"\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"+\3\2\2\2\u009f\u009d\3\2\2\2\r\62\658ORaik\177\u0091\u009d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}