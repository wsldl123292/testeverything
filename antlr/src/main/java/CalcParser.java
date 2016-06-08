// Generated from F:/GitHub/testeverything/antlr/src/main/java\Calc.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WS=8, ID=9, NUMBER=10, 
		ADD=11, SUB=12, MUL=13, DIV=14;
	public static final int
		RULE_exprs = 0, RULE_setExpr = 1, RULE_agmts = 2, RULE_agmt = 3, RULE_calcExpr = 4, 
		RULE_expr = 5, RULE_factor = 6, RULE_funCall = 7, RULE_params = 8;
	public static final String[] ruleNames = {
		"exprs", "setExpr", "agmts", "agmt", "calcExpr", "expr", "factor", "funCall", 
		"params"
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

	@Override
	public String getGrammarFileName() { return "Calc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CalcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExprsContext extends ParserRuleContext {
		public SetExprContext setExpr() {
			return getRuleContext(SetExprContext.class,0);
		}
		public CalcExprContext calcExpr() {
			return getRuleContext(CalcExprContext.class,0);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitExprs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_exprs);
		try {
			setState(20);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				setExpr();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				calcExpr();
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

	public static class SetExprContext extends ParserRuleContext {
		public AgmtsContext agmts() {
			return getRuleContext(AgmtsContext.class,0);
		}
		public SetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitSetExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetExprContext setExpr() throws RecognitionException {
		SetExprContext _localctx = new SetExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_setExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			match(T__0);
			setState(23);
			agmts();
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

	public static class AgmtsContext extends ParserRuleContext {
		public AgmtContext agmt() {
			return getRuleContext(AgmtContext.class,0);
		}
		public AgmtsContext agmts() {
			return getRuleContext(AgmtsContext.class,0);
		}
		public AgmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agmts; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitAgmts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AgmtsContext agmts() throws RecognitionException {
		AgmtsContext _localctx = new AgmtsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_agmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			agmt();
			setState(28);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(26);
				match(T__1);
				setState(27);
				agmts();
				}
				break;
			}
			setState(31);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(30);
				match(T__1);
				}
				break;
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

	public static class AgmtContext extends ParserRuleContext {
		public Token id;
		public Token num;
		public TerminalNode ID() { return getToken(CalcParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(CalcParser.NUMBER, 0); }
		public AgmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitAgmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AgmtContext agmt() throws RecognitionException {
		AgmtContext _localctx = new AgmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_agmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			((AgmtContext)_localctx).id = match(ID);
			setState(34);
			match(T__2);
			setState(35);
			((AgmtContext)_localctx).num = match(NUMBER);
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

	public static class CalcExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CalcExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitCalcExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalcExprContext calcExpr() throws RecognitionException {
		CalcExprContext _localctx = new CalcExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_calcExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(T__3);
			setState(38);
			expr(0);
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

	public static class ExprContext extends ParserRuleContext {
		public Token op;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(CalcParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(CalcParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(CalcParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(CalcParser.SUB, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(41);
			factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(49);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(43);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(44);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(45);
						expr(4);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(46);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(47);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(48);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class FactorContext extends ParserRuleContext {
		public Token sign;
		public Token num;
		public Token id;
		public TerminalNode NUMBER() { return getToken(CalcParser.NUMBER, 0); }
		public TerminalNode ADD() { return getToken(CalcParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(CalcParser.SUB, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(CalcParser.ID, 0); }
		public FunCallContext funCall() {
			return getRuleContext(FunCallContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_factor);
		int _la;
		try {
			setState(64);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				_la = _input.LA(1);
				if (_la==ADD || _la==SUB) {
					{
					setState(54);
					((FactorContext)_localctx).sign = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
						((FactorContext)_localctx).sign = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(57);
				((FactorContext)_localctx).num = match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(T__4);
				setState(59);
				expr(0);
				setState(60);
				match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				((FactorContext)_localctx).id = match(ID);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(63);
				funCall();
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

	public static class FunCallContext extends ParserRuleContext {
		public Token name;
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode ID() { return getToken(CalcParser.ID, 0); }
		public FunCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitFunCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunCallContext funCall() throws RecognitionException {
		FunCallContext _localctx = new FunCallContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((FunCallContext)_localctx).name = match(ID);
			setState(67);
			match(T__4);
			setState(68);
			params();
			setState(69);
			match(T__5);
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

	public static class ParamsContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalcVisitor ) return ((CalcVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			expr(0);
			setState(74);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(72);
				match(T__6);
				setState(73);
				params();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\5\2"+
		"\27\n\2\3\3\3\3\3\3\3\4\3\4\3\4\5\4\37\n\4\3\4\5\4\"\n\4\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\64\n\7\f\7\16\7"+
		"\67\13\7\3\b\5\b:\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bC\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\5\nM\n\n\3\n\2\3\f\13\2\4\6\b\n\f\16\20\22\2\4\3"+
		"\2\17\20\3\2\r\16O\2\26\3\2\2\2\4\30\3\2\2\2\6\33\3\2\2\2\b#\3\2\2\2\n"+
		"\'\3\2\2\2\f*\3\2\2\2\16B\3\2\2\2\20D\3\2\2\2\22I\3\2\2\2\24\27\5\4\3"+
		"\2\25\27\5\n\6\2\26\24\3\2\2\2\26\25\3\2\2\2\27\3\3\2\2\2\30\31\7\3\2"+
		"\2\31\32\5\6\4\2\32\5\3\2\2\2\33\36\5\b\5\2\34\35\7\4\2\2\35\37\5\6\4"+
		"\2\36\34\3\2\2\2\36\37\3\2\2\2\37!\3\2\2\2 \"\7\4\2\2! \3\2\2\2!\"\3\2"+
		"\2\2\"\7\3\2\2\2#$\7\13\2\2$%\7\5\2\2%&\7\f\2\2&\t\3\2\2\2\'(\7\6\2\2"+
		"()\5\f\7\2)\13\3\2\2\2*+\b\7\1\2+,\5\16\b\2,\65\3\2\2\2-.\f\5\2\2./\t"+
		"\2\2\2/\64\5\f\7\6\60\61\f\4\2\2\61\62\t\3\2\2\62\64\5\f\7\5\63-\3\2\2"+
		"\2\63\60\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\r\3\2\2"+
		"\2\67\65\3\2\2\28:\t\3\2\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;C\7\f\2\2<=\7"+
		"\7\2\2=>\5\f\7\2>?\7\b\2\2?C\3\2\2\2@C\7\13\2\2AC\5\20\t\2B9\3\2\2\2B"+
		"<\3\2\2\2B@\3\2\2\2BA\3\2\2\2C\17\3\2\2\2DE\7\13\2\2EF\7\7\2\2FG\5\22"+
		"\n\2GH\7\b\2\2H\21\3\2\2\2IL\5\f\7\2JK\7\t\2\2KM\5\22\n\2LJ\3\2\2\2LM"+
		"\3\2\2\2M\23\3\2\2\2\n\26\36!\63\659BL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}