import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.base.Joiner;
import org.elasticsearch.common.collect.Sets;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.matchAllFilter;
import static org.elasticsearch.search.aggregations.AggregationBuilders.filter;

/**
 * 解析sql生成es查询对象
 */
public class EsUtil2 {

    /**
     * 解析表达式
     *
     * @param obj        表达式对象
     * @param methodFunc 方法名
     * @return 解析后的对象
     */
    private Object invokeMethod(Object obj, String methodFunc) {
        try {
            final Method method = obj.getClass().getMethod(methodFunc);
            return method.invoke(obj);
            /*final Method method = obj.getClass().getMethod(methodFunc, null);
            return method.invoke(obj, null);*/
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解析单个表达式生成比较对象
     *
     * @param expression 表达式
     * @return 比较对象
     */
    protected ObjectExpression processExpression(Expression expression) {
        final ObjectExpression oe = new ObjectExpression();
        final Object columnObj = invokeMethod(expression, "getLeftExpression");
        if (columnObj instanceof LongValue) {
            //如果解析后是 1=1
            final LongValue longValue = (LongValue) columnObj;
            oe.setColumnname(longValue.getStringValue());
        } else {
            final Column column = (Column) invokeMethod(expression, "getLeftExpression");
            if (column != null) {
                oe.setColumnname(column.getColumnName());
            }
        }
        if (expression instanceof BinaryExpression) {
            //对比表达式
            final BinaryExpression be = (BinaryExpression) expression;
            oe.setExp(be.getStringExpression());
            if (be.getRightExpression() instanceof Function) {
                oe.setValue(invokeMethod(be.getRightExpression(), "toString"));
            } else {
                oe.setValue(invokeMethod(be.getRightExpression(), "getValue"));
            }
        } else {
            oe.setExp((String) invokeMethod(expression, "toString"));
        }
        return oe;
    }


    /**
     * 去除表达式的括号
     *
     * @param ex 原始表达式
     * @return 去除括号后的表达式
     */
    protected Expression getExpressionWithoutParenthesis(Expression ex) {
        if (ex instanceof Parenthesis) {
            final Expression child = ((Parenthesis) ex).getExpression();
            return getExpressionWithoutParenthesis(child);
        } else {
            return ex;
        }

    }

    /**
     * 解析sql生成builder
     *
     * @param sql    原始sql
     * @param client es客户端
     * @return es的requestBuilder
     */
    public ActionRequestBuilder convertSql(String sql, Client client) {
        final CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement;
        try {
            statement = parserManager.parse(new StringReader(sql));
            if (statement instanceof Select) {
                PlainSelect plainSelect;
                plainSelect = (PlainSelect) ((Select) statement).getSelectBody();
                final Expression expression = plainSelect.getWhere();

                /**
                 * 包含groupby
                 */
                final List<Expression> expList = plainSelect.getGroupByColumnReferences() == null ? new ArrayList<Expression>() :
                        plainSelect.getGroupByColumnReferences();

                final SearchRequestBuilder searchRequestBuilder = getBasicBuilderWithException(plainSelect, client,expList);
                if (expList.size() == 0) {
                    final FilterBuilder filterBuilder = generateFilter(expression, null);
                    System.out.println(filterBuilder);

                    searchRequestBuilder.setPostFilter(filterBuilder);
                }
                return searchRequestBuilder;
            }

        } catch (JSQLParserException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }


    /**
     * 获取SearchRequestBuilder
     *
     * @param plainSelect 查询条件
     * @param client      es客户端
     * @param expList      groupby的列
     * @return SearchRequestBuilder
     */
    public SearchRequestBuilder getBasicBuilderWithException(PlainSelect plainSelect, Client client,List<Expression> expList) {

        final FromItem item = plainSelect.getFromItem();
        final List<SelectItem> selectItems = plainSelect.getSelectItems();

        /**
         * 包含orderby
         */
        final List<OrderByElement> sqlSelectOrderByItems = plainSelect.getOrderByElements() == null ? new ArrayList<OrderByElement>()
                : plainSelect.getOrderByElements();
        /**
         * 索引集合
         */
        final HashSet<String> indexSet = Sets.newHashSet();
        /**
         * 类型集合
         */
        final HashSet<String> typeSet = Sets.newHashSet();
        final String indexAndTypes = item.toString().split("\\.")[1];
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
        final SearchRequestBuilder searchRequestBuilder = client.prepareSearch(joiner.join(indexSet));

        /**
         * group by后的列
         */
        final List<String> groupByColumns = new ArrayList<>();
        /**
         * 解析group by
         */
        for (Expression ex : expList) {
            if (ex instanceof Column) {
                final Column col = (Column) ex;
                groupByColumns.add(col.getColumnName());
            }
        }

        TermsBuilder termsBuilder;
        /**
         * 包含group by
         */
        if (groupByColumns.size() > 0) {
            /**是groupby的语句*/
            searchRequestBuilder.setSearchType(SearchType.COUNT);
            termsBuilder = AggregationBuilders.terms(groupByColumns.get(groupByColumns.size() - 1)).field(groupByColumns.get(groupByColumns.size() - 1));
            /**
             * 包含order by
             */
            for (OrderByElement orderByElement : sqlSelectOrderByItems) {
                if (orderByElement.getExpression().toString().equals(groupByColumns.get(groupByColumns.size() - 1))) {
                    termsBuilder = termsBuilder.order(Terms.Order.term(orderByElement.isAsc()));
                }
            }
            if (termsBuilder != null) {
                for (int i = groupByColumns.size() - 2; i > -1; i--) {
                    termsBuilder = AggregationBuilders.terms(groupByColumns.get(i)).field(groupByColumns.get(i))
                            .subAggregation(termsBuilder);
                    for (OrderByElement orderByElement : sqlSelectOrderByItems) {
                        if (orderByElement.getExpression().toString().equals(groupByColumns.get(i))) {
                            termsBuilder.order(Terms.Order.term(orderByElement.isAsc()));
                        }
                    }
                }
                final FilterBuilder filterBuilder = generateFilter(plainSelect.getWhere(), null);
                searchRequestBuilder.addAggregation(filter("filter").filter(filterBuilder).subAggregation(termsBuilder));
                //searchRequestBuilder.addAggregation(termsBuilder);
            }
        } else {
            searchRequestBuilder.setSearchType(SearchType.DEFAULT);
            /**
             * 包含order by
             */
            for (OrderByElement orderByElement : sqlSelectOrderByItems) {
                if (orderByElement.isAsc()) {
                    searchRequestBuilder.addSort(orderByElement.getExpression().toString(),
                            SortOrder.ASC);
                } else {
                    searchRequestBuilder.addSort(orderByElement.getExpression().toString(),
                            SortOrder.DESC);
                }
            }
        }

        /**
         * 指定了类型
         */
        if (typeSet.size() > 0) {
            searchRequestBuilder.setTypes(joiner.join(typeSet));
        }

        /**
         * 如果有明确的查询的列名
         */
        if (!(selectItems.size() == 1 && selectItems.get(0).toString().contains("*"))) {
            final String[] returnColumns = new String[selectItems.size()];
            for (int i = 0; i < selectItems.size(); i++) {
                returnColumns[i] = selectItems.get(i).toString();
            }
            searchRequestBuilder.setFetchSource(returnColumns, null);
        }

        /**
         * 包含有limit
         */
        if (plainSelect.getLimit() != null) {
            searchRequestBuilder.setFrom((int) plainSelect.getLimit().getOffset());
            searchRequestBuilder.setSize((int) plainSelect.getLimit().getRowCount());
        }

        return searchRequestBuilder;
    }

    /**
     * 递归生成filter
     *
     * @param ex            表达式
     * @param filterBuilder 过滤器
     * @return FilterBuilder
     */
    private FilterBuilder generateFilter(Expression ex, FilterBuilder filterBuilder) {
        //去除括号
        ex = getExpressionWithoutParenthesis(ex);
        final BinaryExpression be = (BinaryExpression) ex;
        if (ex == null) {
            //没有where条件,查询全部
            return matchAllFilter();
        } else if (be.getLeftExpression() instanceof Column || be.getRightExpression() instanceof Column) {
            //如果是表达式阶段,如 a=1,则直接返回
            return getFilterBuilderWithOperators(processExpression(be));

        } else if (!(be.getLeftExpression() instanceof AndExpression || be.getLeftExpression() instanceof OrExpression ||
                be.getLeftExpression() instanceof Parenthesis) &&
                (!(be.getRightExpression() instanceof AndExpression || be.getRightExpression() instanceof OrExpression ||
                        be.getRightExpression() instanceof Parenthesis))) {
            //a=1 and b=2类似这种格式的执行
            final ObjectExpression left = processExpression(be.getLeftExpression());
            final ObjectExpression right = processExpression(be.getRightExpression());
            if (be instanceof AndExpression) {
                if (filterBuilder == null) {
                    filterBuilder = getFilterBuilderWithOperators(left);
                    filterBuilder = FilterBuilders.andFilter(filterBuilder, getFilterBuilderWithOperators(right));
                } else {
                    filterBuilder = FilterBuilders.andFilter(filterBuilder, getFilterBuilderWithOperators(left));
                    filterBuilder = FilterBuilders.andFilter(filterBuilder, getFilterBuilderWithOperators(right));
                }
            } else if (be instanceof OrExpression) {
                if (filterBuilder == null) {
                    filterBuilder = getFilterBuilderWithOperators(left);
                    filterBuilder = FilterBuilders.orFilter(filterBuilder, getFilterBuilderWithOperators(right));
                } else {
                    filterBuilder = FilterBuilders.orFilter(filterBuilder, getFilterBuilderWithOperators(left));
                    filterBuilder = FilterBuilders.orFilter(filterBuilder, getFilterBuilderWithOperators(right));
                }
            }
            return filterBuilder;
        }
        if (ex instanceof AndExpression) {
            return FilterBuilders.andFilter(generateFilter(be.getLeftExpression(), filterBuilder),
                    generateFilter(be.getRightExpression(), filterBuilder));
        } else if (ex instanceof OrExpression) {
            return FilterBuilders.orFilter(generateFilter(be.getLeftExpression(), filterBuilder),
                    generateFilter(be.getRightExpression(), filterBuilder));
        }

        return null;
    }

    public class ObjectExpression {

        /**
         * 字段
         */
        private String columnname = "";

        /**
         * 对比表达式 比如 <> != is null ....
         */
        private String exp = "";

        /**
         * 值
         */
        private Object value = "";

        /**
         * 获取字段名称
         *
         * @return 字段名称
         */
        public String getColumnname() {
            return columnname;
        }

        /**
         * 设置字段名称
         *
         * @param columnname 字段名称
         */
        public void setColumnname(String columnname) {
            this.columnname = columnname;
        }

        /**
         * 获取操作符
         *
         * @return 操作符
         */
        public String getExp() {
            return exp;
        }

        /**
         * 设置操作符
         *
         * @param exp 操作符
         */
        public void setExp(String exp) {
            this.exp = exp;
        }

        /**
         * 获取值
         *
         * @return 值
         */
        public Object getValue() {
            return value;
        }

        /**
         * 设置值
         *
         * @param value 值
         */
        public void setValue(Object value) {
            this.value = value;
        }
    }

    /**
     * 根据操作符返回过滤器类型
     *
     * @param objectExpression 表达式
     * @return 不同操作的过滤器
     */
    public FilterBuilder getFilterBuilderWithOperators(ObjectExpression objectExpression) {

        switch (objectExpression.exp) {
            case "=":
                return FilterBuilders.termFilter(objectExpression.columnname, objectExpression.getValue());
            case ">":
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
                return FilterBuilders.boolFilter().mustNot(FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value));
            //case "LIKE" : return FilterBuilders.boolFilter().should(FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value));
            //case "LIKE" : return FilterBuilders.prefixFilter(objectExpression.columnname,objectExpression.value.toString());
            default:
                return null;
        }
    }
}


