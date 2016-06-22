package esql;

import java.util.Stack;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/15.
 */
public class EsqlVisitor extends SQLParserBaseVisitor<String> {

    public static Stack<String> expr = new Stack<>();
    public static Stack<String> op = new Stack<>();

    @Override
    public String visitParenExp(SQLParser.ParenExpContext ctx) {
        //System.out.println("111111");
        return visitChildren(ctx);
    }

    @Override
    public String visitAndExp(SQLParser.AndExpContext ctx) {
        //System.out.println("2222222");
        System.out.println(ctx.expression(0).getText() + "," + ctx.expression(0).getChildCount());
        System.out.println(ctx.expression(1) instanceof SQLParser.ExpContext);
        op.add("and");
        return visitChildren(ctx);
    }

    @Override
    public String visitOrExp(SQLParser.OrExpContext ctx) {
        //System.out.println("333333");
        op.add("or");
        return visitChildren(ctx);
    }

    @Override
    public String visitExp(SQLParser.ExpContext ctx) {
        //System.out.println("444444");
        //System.out.println(ctx.getText());
        expr.add(ctx.getText());
        return visitChildren(ctx);
    }
}
