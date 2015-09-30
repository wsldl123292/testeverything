import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/25 16:42
 */
public class Test {
    public static void main(String[] args) {
        /*final Client client = new TransportClient()
                //.addTransportAddress(new InetSocketTransportAddress("111.204.239.215", 9300));
                .addTransportAddress(new InetSocketTransportAddress("192.168.1.205", 9300));
        //sql格式  SELECT * from es.db1$w2$$db2$d3_ww  es.索引1$类型$$索引2$类型
        String sql = "select * from es.cms_data_internetdb$info where nickname = 'test1' and nickname = 'test'";


        // parser得到AST
        final SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
        final List<SQLStatement> stmtList = parser.parseStatementList();
        for (SQLStatement stmt : stmtList) {
            if (stmt instanceof SQLSelectStatement) {
                final SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
                final SQLSelect sqlselect = sstmt.getSelect();
                final MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) sqlselect.getQuery();
                final SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) query.getWhere();

                Node<String> root = new Node<>(sqlBinaryOpExpr.getOperator().toString(), -1);
                MyTree<String> tree = new MyTree<>(root);

                Node<String> tempNode = root;
                while (!(sqlBinaryOpExpr.getLeft() instanceof SQLIdentifierExpr)) {
                    Node<String> node = new Node<>(sqlBinaryOpExpr.getLeft().toString());
                    tree.add(node, tempNode);
                }


                FilterBuilder filterBuilder = null;
                List<Node<String>> nodes = tree.getAllNodes();
                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).getData().equals("BooleanAnd")) {
                        continue;
                    }
                    SQLExprParser exprParser = new SQLExprParser(nodes.get(i).getData());
                    SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) exprParser.expr();
                    if (filterBuilder == null) {
                        filterBuilder = getFilterBuilderWithOperators(binaryOpExpr);
                    } else {
                        filterBuilder = FilterBuilders.andFilter(filterBuilder, getFilterBuilderWithOperators(binaryOpExpr));
                    }
                }


                System.out.println(filterBuilder);
                final SearchRequestBuilder searchRequestBuilder = client.prepareSearch("cms_data_internetdb")
                        .setTypes("info")
                        .setSearchType(SearchType.DEFAULT)
                        .setPostFilter(filterBuilder);

                final SearchResponse response = searchRequestBuilder
                        .execute().actionGet();

                final SearchHits hits = response.getHits();
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsString());
                }
            }
        }*/
        /*ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(() -> {
            System.out.println("call execute..");
            TimeUnit.SECONDS.sleep(1);
            return 7;
        });
        System.exit(1);*/
    }


    /**
     * 根据操作符返回过滤器类型
     *
     * @param binaryOpExpr 表达式
     * @return 不同操作的过滤器
     */
    public static FilterBuilder getFilterBuilderWithOperators(SQLBinaryOpExpr binaryOpExpr) {

        switch (binaryOpExpr.getOperator().name) {
            case "=":
                return FilterBuilders.termFilter(binaryOpExpr.getLeft().toString(), binaryOpExpr.getRight().toString());
            /*case ">":
                return FilterBuilders.rangeFilter(objectExpression.columnname).gt(objectExpression.value);
            case ">=":
                return FilterBuilders.rangeFilter(objectExpression.columnname).gte(objectExpression.value);
            case "<":
                return FilterBuilders.rangeFilter(objectExpression.columnname).lt(objectExpression.value);
            case "<=":
                return FilterBuilders.rangeFilter(objectExpression.columnname).lte(objectExpression.value);
            case "!=":
                return FilterBuilders.boolFilter().mustNot(FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value));
            case "<>":
                return FilterBuilders.boolFilter().mustNot(FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value));*/
            //case "LIKE" : return FilterBuilders.boolFilter().should(FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value));
            //case "LIKE" : return FilterBuilders.prefixFilter(objectExpression.columnname,objectExpression.value.toString());
            default:
                return null;
        }
    }
}
