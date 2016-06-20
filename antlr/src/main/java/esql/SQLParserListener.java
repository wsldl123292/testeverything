// Generated from F:/GitHub/testeverything/antlr/src/main/java/esql\SQLParser.g4 by ANTLR 4.5.3
package esql;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLParser}.
 */
public interface SQLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(SQLParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(SQLParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#select_clause}.
	 * @param ctx the parse tree
	 */
	void enterSelect_clause(SQLParser.Select_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#select_clause}.
	 * @param ctx the parse tree
	 */
	void exitSelect_clause(SQLParser.Select_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(SQLParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(SQLParser.Table_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#table_type}.
	 * @param ctx the parse tree
	 */
	void enterTable_type(SQLParser.Table_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#table_type}.
	 * @param ctx the parse tree
	 */
	void exitTable_type(SQLParser.Table_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#limit_case}.
	 * @param ctx the parse tree
	 */
	void enterLimit_case(SQLParser.Limit_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#limit_case}.
	 * @param ctx the parse tree
	 */
	void exitLimit_case(SQLParser.Limit_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#limitexpre}.
	 * @param ctx the parse tree
	 */
	void enterLimitexpre(SQLParser.LimitexpreContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#limitexpre}.
	 * @param ctx the parse tree
	 */
	void exitLimitexpre(SQLParser.LimitexpreContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#limitNum}.
	 * @param ctx the parse tree
	 */
	void enterLimitNum(SQLParser.LimitNumContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#limitNum}.
	 * @param ctx the parse tree
	 */
	void exitLimitNum(SQLParser.LimitNumContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(SQLParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(SQLParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#column_list_clause}.
	 * @param ctx the parse tree
	 */
	void enterColumn_list_clause(SQLParser.Column_list_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#column_list_clause}.
	 * @param ctx the parse tree
	 */
	void exitColumn_list_clause(SQLParser.Column_list_clauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void enterWhere_clause(SQLParser.Where_clauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#where_clause}.
	 * @param ctx the parse tree
	 */
	void exitWhere_clause(SQLParser.Where_clauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExp(SQLParser.AndExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExp(SQLParser.AndExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExp(SQLParser.ParenExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExp(SQLParser.ParenExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExp(SQLParser.OrExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExp(SQLParser.OrExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExp(SQLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exp}
	 * labeled alternative in {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExp(SQLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(SQLParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(SQLParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#right_element}.
	 * @param ctx the parse tree
	 */
	void enterRight_element(SQLParser.Right_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#right_element}.
	 * @param ctx the parse tree
	 */
	void exitRight_element(SQLParser.Right_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#left_element}.
	 * @param ctx the parse tree
	 */
	void enterLeft_element(SQLParser.Left_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#left_element}.
	 * @param ctx the parse tree
	 */
	void exitLeft_element(SQLParser.Left_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#target_element}.
	 * @param ctx the parse tree
	 */
	void enterTarget_element(SQLParser.Target_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#target_element}.
	 * @param ctx the parse tree
	 */
	void exitTarget_element(SQLParser.Target_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#relational_op}.
	 * @param ctx the parse tree
	 */
	void enterRelational_op(SQLParser.Relational_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#relational_op}.
	 * @param ctx the parse tree
	 */
	void exitRelational_op(SQLParser.Relational_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#expr_op}.
	 * @param ctx the parse tree
	 */
	void enterExpr_op(SQLParser.Expr_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#expr_op}.
	 * @param ctx the parse tree
	 */
	void exitExpr_op(SQLParser.Expr_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#between_op}.
	 * @param ctx the parse tree
	 */
	void enterBetween_op(SQLParser.Between_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#between_op}.
	 * @param ctx the parse tree
	 */
	void exitBetween_op(SQLParser.Between_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#is_or_is_not}.
	 * @param ctx the parse tree
	 */
	void enterIs_or_is_not(SQLParser.Is_or_is_notContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#is_or_is_not}.
	 * @param ctx the parse tree
	 */
	void exitIs_or_is_not(SQLParser.Is_or_is_notContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void enterSimple_expression(SQLParser.Simple_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void exitSimple_expression(SQLParser.Simple_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#table_references}.
	 * @param ctx the parse tree
	 */
	void enterTable_references(SQLParser.Table_referencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#table_references}.
	 * @param ctx the parse tree
	 */
	void exitTable_references(SQLParser.Table_referencesContext ctx);
}