package esql;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/15.
 */
public class EsqlVisitor extends SQLParserBaseVisitor<QueryBuilder> {

    @Override
    public QueryBuilder visitParenExp(SQLParser.ParenExpContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public QueryBuilder visitAndExp(SQLParser.AndExpContext ctx) {
        //System.out.println("2222222");
        QueryBuilder left = visit(ctx.expression(0));
        QueryBuilder right = visit(ctx.expression(1));
        return QueryBuilders.boolQuery()
                .must(left)
                .must(right);

    }

    @Override
    public QueryBuilder visitOrExp(SQLParser.OrExpContext ctx) {
        QueryBuilder left = visit(ctx.expression(0));
        QueryBuilder right = visit(ctx.expression(1));
        return QueryBuilders.boolQuery()
                .should(left)
                .should(right);
    }

    @Override
    public QueryBuilder visitExp(SQLParser.ExpContext ctx) {
        SQLParser.Simple_expressionContext simple_expressionContext = ctx.simple_expression();
        if(simple_expressionContext instanceof SQLParser.BaseExpContext){
            SQLParser.BaseExpContext baseExpContext = (SQLParser.BaseExpContext) simple_expressionContext;
            if(baseExpContext.relational_op().getText().equals("=")){
                return QueryBuilders.matchPhraseQuery(baseExpContext.left_element().getText(), baseExpContext.right_element().getText());
            }
        }
        return visitChildren(ctx);
    }
}
