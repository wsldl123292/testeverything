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
        SQLParser.ExpressionContext left = ctx.expression(0);
        SQLParser.ExpressionContext right = ctx.expression(1);
        System.out.println(ctx.getText());
        if(left.getChildCount()==1){
            System.out.println(left.getText());
        }else if(right.getChildCount()==1){
            System.out.println(right.getText());
        }
        return visitChildren(ctx);

    }

    @Override
    public String visitOrExp(SQLParser.OrExpContext ctx) {
        SQLParser.ExpressionContext left = ctx.expression(0);
        SQLParser.ExpressionContext right = ctx.expression(1);
        if(left.getChildCount()==1){
            System.out.println(left.getText());
            return visitChildren(right);
        }else if(right.getChildCount()==1){
            System.out.println(right.getText());
            return visitChildren(left);
        }
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
