package tour.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/13.
 */
public class ExprJoyRide {

    public static void main(String[] args) throws Exception {
        String inputFile = "F:\\GitHub\\testeverything\\antlr\\src\\main\\java\\tour\\expr\\t.expr";
        InputStream is =  new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog(); // parse; start at prog 15
        System.out.println(tree.toStringTree(parser)); // print tree as text
    }
}
