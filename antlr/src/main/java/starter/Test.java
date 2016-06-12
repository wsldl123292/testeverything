package starter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.Scanner;

/**
 * 说明:
 * 作者: LDL
 * 日期: 2016/6/12.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line != null) {
                    line = line.trim();
                    if (line.length() != 0) {
                        if ("exit".equals(line) || "bye".equals(line))
                            break;
                        ANTLRInputStream input = new ANTLRInputStream(line);
                        ArrayInitLexer lexer = new ArrayInitLexer(input);

                        CommonTokenStream tokens = new CommonTokenStream(lexer);

                        ArrayInitParser parser = new ArrayInitParser(tokens);

                        ParseTree tree = parser.init();
                        System.out.println(tree.toStringTree(parser));
                    }
                }

            }
        }


    }
}
