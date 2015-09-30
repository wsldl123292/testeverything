import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBooleanExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectOrderByItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.util.JdbcUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.base.Joiner;
import org.elasticsearch.common.collect.Sets;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

import java.util.HashSet;
import java.util.List;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/8/23 13:34
 */
public class druidTest {
    static final Client client = new TransportClient()
            //.addTransportAddress(new InetSocketTransportAddress("111.204.239.215", 9300));
            .addTransportAddress(new InetSocketTransportAddress("192.168.1.205", 9300));

    public static void main(String[] args) {

        /*String sql ="select * from cms_data_internetdb where (a =1 or b = 2) and c = 2";
        StringBuffer from = new StringBuffer();
        StringBuffer where = new StringBuffer();
        // parser得到AST
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
        List<SQLStatement> stmtList = parser.parseStatementList(); //*/

        // 将AST通过visitor输出
        //SQLASTOutputVisitor visitor = SQLUtils.createFormatOutputVisitor(from, stmtList, JdbcUtils.MYSQL);
        //SQLASTOutputVisitor whereVisitor = SQLUtils.createFormatOutputVisitor(where, stmtList, JdbcUtils.MYSQL);
        /*for (SQLStatement stmt : stmtList) {
            //stmt.accept(visitor);
            if (stmt instanceof SQLSelectStatement) {
                SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
                SQLSelect sqlselect = sstmt.getSelect();
                SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlselect.getQuery();
                *//*query.getFrom().accept(visitor);
                query.getWhere().accept(whereVisitor);*//*
                SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) query.getWhere();
                System.out.println(binaryOpExpr.getLeft());
                System.out.println(query.getSelectList());
                *//*while(!(binaryOpExpr.getLeft() instanceof SQLIdentifierExpr)){
                    SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) binaryOpExpr.getLeft();
                    System.out.println(sqlBinaryOpExpr.getLeft());
                    binaryOpExpr = sqlBinaryOpExpr;
                }
                System.out.println(query.getSelectList());*//*
            }
            *//*System.out.println(select);
            System.out.println(from.toString());
            System.out.println(where);*//*
        }*/
        /*StringBuffer select = new StringBuffer();
        SQLExprParser exprParser = new SQLExprParser("a = 5");
        SQLExpr expr = SQLUtils.toSQLExpr("select * from cms_data_internetdb where a =1 and b = 2");
        expr.output(select);
        System.out.println(select);
        SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) exprParser.expr();
        System.out.println(binaryOpExpr.getOperator());

        SQLIdentifierExpr left = (SQLIdentifierExpr) binaryOpExpr.getLeft();
        SQLIntegerExpr right = (SQLIntegerExpr) binaryOpExpr.getRight();

        System.out.println(left.getName());
        System.out.println(right.getNumber().intValue());*/


        /*String sql = "SELECT * FROM T WHERE F1 = 1 and F2 = 2";
        Lexer lexer = new Lexer(sql);
        for (;;) {
            lexer.nextToken();
            Token tok = lexer.token();

            if (tok == Token.IDENTIFIER) {
                System.out.println(tok.name() + "\t\t" + lexer.stringVal());
            } else if (tok == Token.LITERAL_INT) {
                System.out.println(tok.name() + "\t\t" + lexer.numberString());
            } else {
                System.out.println(tok.name() + "\t\t\t" + tok.name);
            }

            if (tok == Token.WHERE) {
                System.out.println("where pos : " + lexer.pos());
            }

            if (tok == Token.EOF) {
                break;
            }
        }*/

        /*SQLExprParser exprParser = new SQLExprParser("BooleanAnd");
        SQLBinaryOpExpr binaryOpExpr = (SQLBinaryOpExpr) exprParser.expr();
        System.out.println(binaryOpExpr.getOperator());*/
        //sql格式  SELECT * from es.db1$w2$$db2$d3_ww  es.索引1$类型$$索引2$类型
        String sql = "select * from es.cms_data_internetdb$info where a = b and a= 1 and b=2";
        // parser得到AST
        final SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, JdbcUtils.MYSQL);
        final List<SQLStatement> stmtList = parser.parseStatementList();
        for (SQLStatement stmt : stmtList) {
            if (stmt instanceof SQLSelectStatement) {
                final SQLSelectStatement sstmt = (SQLSelectStatement) stmt;
                final SQLSelect sqlselect = sstmt.getSelect();
                final MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) sqlselect.getQuery();

                SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) query.getWhere();
                System.out.println(sqlBinaryOpExpr.getLeft());
                System.out.println(sqlBinaryOpExpr.getLeft() instanceof SQLBooleanExpr);
                /**
                 * 有查询条件
                 */
                if (query.getWhere() != null) {

                } else {
                    //没有where条件,查询全部
                    //matchAllQuery(query);
                }


                /*SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) binaryOpExpr.getRight();
                while(!(sqlBinaryOpExpr.getLeft() instanceof SQLIdentifierExpr)){
                    sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlBinaryOpExpr.getLeft();
                    System.out.println(sqlBinaryOpExpr.getLeft()+" "+sqlBinaryOpExpr.getOperator()+" "+sqlBinaryOpExpr.getRight());
                    if(sqlBinaryOpExpr.getLeft() instanceof SQLIdentifierExpr){
                        sqlBinaryOpExpr = (SQLBinaryOpExpr)binaryOpExpr.getRight();
                    }
                }*/

            }
        }
    }


    /**
     * 查询全部方法(没有查询条件)
     *
     * @param queryBlock 查询区对象
     */
    public static void matchAllQuery(MySqlSelectQueryBlock queryBlock) {

        //sql格式  SELECT * from es.db1$w2$$db2$d3_ww  es.索引1$类型$$索引2$类型
        /**
         * 索引集合
         */
        final HashSet<String> indexSet = Sets.newHashSet();
        /**
         * 类型集合
         */
        final HashSet<String> typeSet = Sets.newHashSet();
        final String indexAndTypes = queryBlock.getFrom().toString().split("\\.")[1];
        final String[] indexAndTypeArr = indexAndTypes.split("\\$\\$");
        for (String anIndexAndTypeArr : indexAndTypeArr) {
            final String[] arr = anIndexAndTypeArr.split("\\$");
            indexSet.add(arr[0]);
            //只匹配一个索引带一个类型
            if (arr.length == 2) {
                typeSet.add(arr[1]);
            }
        }
        /**
         * 将集合格式化为以逗号隔开的方式
         */
        final Joiner joiner = Joiner.on(",");

        /**
         * 构建 SearchRequestBuilder
         */
        final SearchRequestBuilder searchRequestBuilder = client.prepareSearch(joiner.join(indexSet))
                .setSearchType(SearchType.DEFAULT)
                .setPostFilter(FilterBuilders.orFilter(FilterBuilders.termFilter("nickname", "test"),FilterBuilders.termFilter("nickname", "test1")));

        FilterBuilder filterBuilder = FilterBuilders.termFilter("nickname", "test");
        System.out.println(filterBuilder);
        filterBuilder = FilterBuilders.orFilter(filterBuilder,FilterBuilders.termFilter("nickname", "test1"));
        System.out.println(filterBuilder);
        /**
         * 指定了类型
         */
        if (typeSet.size() > 0) {
            searchRequestBuilder.setTypes(joiner.join(typeSet));
        }

        /**
         * 如果有明确的查询的列名
         */
        if (!(queryBlock.getSelectList().size() == 1 && queryBlock.getSelectList().get(0).toString().contains("*"))) {
            final List<SQLSelectItem> sqlSelectItems = queryBlock.getSelectList();
            final String[] returnColumns = new String[sqlSelectItems.size()];
            for (int i = 0; i < sqlSelectItems.size(); i++) {
                returnColumns[i] = sqlSelectItems.get(i).toString();
            }
            searchRequestBuilder.setFetchSource(returnColumns, null);
        }

        /**
         * 包含有limit
         */
        if (queryBlock.getLimit() != null) {
            searchRequestBuilder.setFrom(Integer.parseInt(queryBlock.getLimit().getOffset().toString()));
            searchRequestBuilder.setSize(Integer.parseInt(queryBlock.getLimit().getRowCount().toString()));
        }

        /**
         * 包含order by
         */
        if (queryBlock.getOrderBy() != null) {
            final List<SQLSelectOrderByItem> sqlSelectOrderByItems = queryBlock.getOrderBy().getItems();
            for (SQLSelectOrderByItem sqlSelectOrderByItem : sqlSelectOrderByItems) {
                searchRequestBuilder.addSort(sqlSelectOrderByItem.getExpr().toString(), SortOrder.valueOf(sqlSelectOrderByItem.getType().toString()));
            }

        }

        final SearchResponse response = searchRequestBuilder
                .execute().actionGet();

        final SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }
}
