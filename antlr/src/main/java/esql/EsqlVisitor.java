package esql;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/15.
 */
public class EsqlVisitor extends SQLParserBaseVisitor<String> {

    @Override
    public String visitTable_name(SQLParser.Table_nameContext ctx) {
        //System.out.println(ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public String visitParenExp(SQLParser.ParenExpContext ctx) {
        System.out.println("111111");
        return visitChildren(ctx);
    }

    @Override
    public String visitAndExp(SQLParser.AndExpContext ctx) {
        System.out.println("2222222");
        return visitChildren(ctx);
    }

    @Override public String visitOrExp(SQLParser.OrExpContext ctx) {
        System.out.println("333333");
        return visitChildren(ctx);
    }

    @Override
    public String visitExp(SQLParser.ExpContext ctx) {
        System.out.println("444444");
        System.out.println(ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public String visitBaseExp(SQLParser.BaseExpContext ctx) {
        System.out.println("base");
        System.out.println(ctx.getText());
        return visitChildren(ctx);
    }
}
