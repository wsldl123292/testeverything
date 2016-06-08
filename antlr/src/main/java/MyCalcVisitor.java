/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/8.
 */
public class MyCalcVisitor extends CalcBaseVisitor<Double> {

    @Override
    public Double visitExprs(CalcParser.ExprsContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Double visitAgmt(CalcParser.AgmtContext ctx) {
        Context.getInstance().setContext(ctx.id.getText(), ctx.num.getText());
        return null;
    }

    @Override
    public Double visitAgmts(CalcParser.AgmtsContext ctx) {
        visit(ctx.agmt());
        if (ctx.agmts() != null)
            visit(ctx.agmts());
        return null;
    }

    @Override
    public Double visitCalcExpr(CalcParser.CalcExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitExpr(CalcParser.ExprContext ctx) {
        int cc = ctx.getChildCount();
        if (cc == 3) {
            switch (ctx.op.getType()) {
                case CalcParser.ADD:
                    return visit(ctx.expr(0)) + visit(ctx.expr(1));
                case CalcParser.SUB:
                    return visit(ctx.expr(0)) - visit(ctx.expr(1));
                case CalcParser.MUL:
                    return visit(ctx.expr(0)) * visit(ctx.expr(1));
                case CalcParser.DIV:
                    return visit(ctx.expr(0)) / visit(ctx.expr(1));
            }
        } else if (cc == 1) {
            return visit(ctx.getChild(0));
        }
        throw new RuntimeException();
    }

    @Override
    public Double visitFactor(CalcParser.FactorContext ctx) {
        int cc = ctx.getChildCount();
        if (cc == 3) {
            return visit(ctx.getChild(1));
        } else if (cc == 2) {
            if (ctx.sign.getType() == CalcParser.ADD)
                return Double.valueOf(ctx.getChild(1).getText());
            if (ctx.sign.getType() == CalcParser.SUB)
                return -1 * Double.valueOf(ctx.getChild(1).getText());
        } else if (cc == 1) {
            if (ctx.num != null)
                return Double.valueOf(ctx.getChild(0).getText());
            if (ctx.id != null)
                return Context.getInstance().getValue(ctx.id.getText());
            return visit(ctx.funCall());
        }
        throw new RuntimeException();
    }

    @Override
    public Double visitParams(CalcParser.ParamsContext ctx) {
        if (ctx.params() != null)
            visit(ctx.params());
        Context.getInstance().pushStack(visit(ctx.expr()));
        return null;
    }

    @Override
    public Double visitFunCall(CalcParser.FunCallContext ctx) {
        visit(ctx.params());
        String funName = ctx.name.getText();
        switch (funName) {
            case "pow":
                return Math.pow(Context.getInstance().popStack(), Context.getInstance().popStack());
            case "sqrt":
                return Math.sqrt(Context.getInstance().popStack());
        }
        throw new RuntimeException();
    }

    @Override
    public Double visitSetExpr(CalcParser.SetExprContext ctx) {
        return visit(ctx.agmts());
    }

}