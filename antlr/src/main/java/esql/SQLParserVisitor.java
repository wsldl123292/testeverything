// Generated from /Users/ldl/Documents/develop/project/github/testeveryting/antlr/src/main/java/esql/SQLParser.g4 by ANTLR 4.5.3
package esql;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQLParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(SQLParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#select_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_clause(SQLParser.Select_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(SQLParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#table_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_type(SQLParser.Table_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#limit_case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit_case(SQLParser.Limit_caseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#limitexpre}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitexpre(SQLParser.LimitexpreContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#limitNum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitNum(SQLParser.LimitNumContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(SQLParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#column_list_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_list_clause(SQLParser.Column_list_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#where_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere_clause(SQLParser.Where_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(SQLParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#right_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRight_element(SQLParser.Right_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#left_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeft_element(SQLParser.Left_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#target_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTarget_element(SQLParser.Target_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#relational_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational_op(SQLParser.Relational_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#expr_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_op(SQLParser.Expr_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#between_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween_op(SQLParser.Between_opContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#is_or_is_not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_or_is_not(SQLParser.Is_or_is_notContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#simple_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_expression(SQLParser.Simple_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#table_references}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_references(SQLParser.Table_referencesContext ctx);
}