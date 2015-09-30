import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/8/23 10:59
 */
public class jsqlTest {
    public static void main(String[] args) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("SELECT * FROM MY_TABLE1,my_table2");
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
        System.out.println(tableList);

        Select select = (Select) CCJSqlParserUtil.parse("select a,b,c from test where a=1");
        /*final AddAliasesVisitor instance = new AddAliasesVisitor();
        select.getSelectBody().accept(instance);*/
        System.out.println(select.getSelectBody());
        System.out.println(select.getWithItemsList());
    }
}
