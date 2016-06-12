package calc;// Generated from F:/GitHub/testeverything/antlr/src/main/java\calc.Calc.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalcParser#exprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprs(CalcParser.ExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#setExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetExpr(CalcParser.SetExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#agmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgmts(CalcParser.AgmtsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#agmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgmt(CalcParser.AgmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#calcExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcExpr(CalcParser.CalcExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CalcParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(CalcParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#funCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(CalcParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(CalcParser.ParamsContext ctx);
}