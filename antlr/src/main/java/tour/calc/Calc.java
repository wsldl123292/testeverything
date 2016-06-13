package tour.calc;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import tour.expr.ExprLexer;
import tour.expr.ExprParser;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/13.
 */
public class Calc {

    public static void main(String[] args) throws Exception {
        String inputFile = "F:\\GitHub\\testeverything\\antlr\\src\\main\\java\\tour\\expr\\t.expr";
        InputStream is =  new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog(); // parse; start at prog 15
        //System.out.println(tree.toStringTree(parser)); // print tree as text
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);

    }
}
