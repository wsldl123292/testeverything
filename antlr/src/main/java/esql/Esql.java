package esql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Stack;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/15.
 */
public class Esql {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("F:\\GitHub\\testeverything\\antlr\\src\\main\\java\\esql\\e.txt");
        //InputStream is = new FileInputStream("/Users/ldl/Documents/develop/project/github/testeveryting/antlr/src/main/java/esql/e.txt");
        ANTLRInputStream input = new ANTLRInputStream(is);
        SQLLexer lexer = new SQLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLParser parser = new SQLParser(tokens);
        ParseTree tree = parser.stat();
        EsqlVisitor eval = new EsqlVisitor();
        eval.visit(tree);
        System.out.println(tree.toStringTree(parser)); // print tree as text

    }
}
