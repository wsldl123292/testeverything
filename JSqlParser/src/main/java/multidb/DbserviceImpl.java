package multidb;


import com.alibaba.fastjson.JSON;
import multidb.ExpressionNode.*;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.StatementDeParser;

import java.io.StringReader;
import java.util.*;

public class DbserviceImpl {

    /**
     * sql解析管理
     */
    final CCJSqlParserManager parserManager = new CCJSqlParserManager();

    /**
     * 开始执行sql
     *
     * @param sql 原始sql
     * @return node
     */
    private AbstractNode startSql(String sql) {
        AbstractNode root;
        PlainSelect plainSelect = null;
        Statement statement = null;
        try {
            statement = parserManager.parse(new StringReader(sql));
            plainSelect = (PlainSelect) ((Select) statement).getSelectBody();
        } catch (JSQLParserException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        FromItem item = null;
        //List<SelectItem> selectItems = null;
        if (plainSelect != null) {
            item = plainSelect.getFromItem();
            //selectItems = plainSelect.getSelectItems();
        }

        /*final List<Function> funList = new ArrayList<>();

        for (SelectItem selectItem : selectItems) {
            if (!selectItem.toString().equals("*")) {
                final SelectExpressionItem seItem = (SelectExpressionItem) selectItem;

                final Expression ee = seItem.getExpression();
                if (ee instanceof Function) {
                    final Function fun = (Function) ee;
                    final String funName = fun.getName().toLowerCase().trim();
                    if (!(funName.equals("max") || Objects.equals(funName, "min") || Objects.equals(funName, "sum") ||
                            Objects.equals(funName, "count") || Objects.equals(funName, "avg"))) {
                        funList.add(fun);
                    }
                }
            }
        }*/

        String schema = "";
        if (item instanceof Table) {
            final Table tt = (Table) item;
            schema = tt.getSchemaName();
        }

        root = createScanNodeWithoutSchema(schema, statement, null);// 无函数时，直接送底层数据库
        /*if (funList.size() == 0) {
            root = createScanNodeWithoutSchema(schema, statement, null);// 无函数时，直接送底层数据库
        } else {

        }*/

        /*if (root != null) {
            root.exec();

            while (!root.isComplete() && root.getStatus() == AbstractNode.EXESTATUS.EXE_PENDING) {

                System.out.println("正在执行...");
            }
            System.out.println(((ElasticSearchScanNode) root).getFullTupleToString());
        }*/

        return root;
    }

    /**
     * 创建node
     *
     * @param schema    schema
     * @param statement statement
     * @param parent    parent
     * @return node
     */
    private AbstractNode createScanNodeWithoutSchema(String schema, Statement statement, AbstractNode parent) {

        final PlainSelect plainSelect = (PlainSelect) ((Select) statement).getSelectBody();
        final FromItem item = plainSelect.getFromItem();
        //PhoenixScanNode phoenixScanNode;
        ElasticSearchScanNode esScanNode;
        if (item instanceof Table) {
            final Table tt = (Table) item;
            tt.setSchemaName(null);
        }

        final StringBuilder builder = new StringBuilder();
        final StatementDeParser deParser = new StatementDeParser(builder);
        statement.accept(deParser);

        if (Objects.equals(schema, "hbase")) {
            /*phoenixScanNode = new PhoenixScanNode(deParser.getBuffer().toString(),
                    parent);
            return phoenixScanNode;*/
        } else if (Objects.equals(schema, "es")) {
            esScanNode = new ElasticSearchScanNode(deParser.getBuffer()
                    .toString(), parent);
            return esScanNode;
        }

        return null;
    }

    public AbstractNode select_ret_file(String sql, QueryCallback cb) {
        // TODO Auto-generated method stub

        final AbstractNode scanNode = startSql(sql);

        final LocalFileResultNode fileNode = new LocalFileResultNode(scanNode, cb);

        fileNode.exec();
        return fileNode;
    }

    public String select_ret_json(String sql) {

        System.out.println("sql......" + sql);
        // TODO Auto-generated method stub
        // 解析出表空间
        // 重写SQL发送到对应的表空间
        final AbstractNode root = startSql(sql);
        root.exec();
        while (!root.isComplete() && root.getStatus() == AbstractNode.EXESTATUS.EXE_SUCCESS) {

            System.out.println("正在执行...");
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
        }
        Map<String, Object> map = new LinkedHashMap<>();

        /**
         * 执行失败
         */
        if (root.getStatus() == AbstractNode.EXESTATUS.EXE_ERROR) {
            map.put("error", "sql to elasticsearch 发生错误，请联系管理员");
            return JSON.toJSONString(map);
        }
        final List<Map<String, Object>> data = new ArrayList<>();
        while (true) {
            Map<String, Object> obj = root.getNextTuple();
            if (obj == null)
                break;
            data.add(obj);

        }
        map.put("data", data);
        map.put("took", root.getTook());
        map.put("total", root.getTotal());
        return JSON.toJSONString(map);

    }

    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String sql = "select myweb,nickname from es.cms_data_internetdb$info where publishnum=0";

                final DbserviceImpl dbservice = new DbserviceImpl();
                dbservice.select_ret_file(sql, new QueryCallback() {

                    @Override
                    public void callback(String node, AbstractNode.EXESTATUS status) {
                        System.out.println(node + "  " + status);
                    }

                });
                System.out.println();
            }
        }, "test2").start();


        /*Map<String,String> map = new HashMap<>();
        map.put("title","\"一个入市一星期便遇<span style=\"color:red\">股</span>灾的新<span style=\"color:red\">股</span>民的悲壮认识\"");

        System.out.println(JSON.toJSONString(map));*/
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                final String sql = "select * from es.cms_data_internetdb$info where nickname = 'dms1'";
                final DbserviceImpl dbservice = new DbserviceImpl();
                System.out.println(dbservice.select_ret_json(sql));
            }
        }, "test3").start();*/

    }

}
