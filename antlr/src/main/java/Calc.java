import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/6.
 */

public class Calc {
    private static final String lineStart = "CALC> ";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(lineStart);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line != null) {
                    line = line.trim();
                    if (line.length() != 0) {
                        if ("exit".equals(line) || "bye".equals(line))
                            break;
                        ANTLRInputStream input = new ANTLRInputStream(line);
                        CalcLexer lexer = new CalcLexer(input);
                        CommonTokenStream tokens = new CommonTokenStream(lexer);
                        CalcParser parser = new CalcParser(tokens);
                        ParseTree tree = parser.exprs();
                        MyCalcVisitor mv = new MyCalcVisitor();
                        Double res = mv.visit(tree);
                        if (res != null)
                            System.out.println(res);
                    }
                }

                System.out.print(lineStart);
            }
        }
    }
}
