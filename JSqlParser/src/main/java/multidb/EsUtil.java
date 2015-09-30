package multidb;

import com.google.common.base.Splitter;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Sets;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static org.elasticsearch.index.query.FilterBuilders.matchAllFilter;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * 解析sql生成es查询对象
 */
public class EsUtil {

    private QueryStringQueryBuilder queryBuilder = null;

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
        //ArrayList<String> list = new ArrayList<>();
        Statement statement;
        try {
            statement = parserManager.parse(new StringReader(sql));
            if (statement instanceof Select) {
                PlainSelect plainSelect;
                plainSelect = (PlainSelect) ((Select) statement).getSelectBody();
                final Expression expression = plainSelect.getWhere();
                final FilterBuilder filterBuilder = generateFilter(expression, null);
                System.out.println("builder " + filterBuilder);
                final SearchRequestBuilder searchRequestBuilder = getBasicBuilderWithException(plainSelect, client);
                searchRequestBuilder.setPostFilter(filterBuilder);
                System.out.println("searchRequestBuilder " + searchRequestBuilder);
                return searchRequestBuilder;
                /**
                 * 自定义函数
                 *//*
                final List<Function> funList = new ArrayList<>();
                final List<SelectItem> selectItems = plainSelect.getSelectItems();
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
                }

                final SearchResponse response = searchRequestBuilder.setExplain(true).execute().actionGet();

                //Thread.sleep(10000);
                final SearchHits searchHits = response.getHits();

                for (SearchHit searchHit : searchHits) {
                    list.add(searchHit.getSourceAsString());
                }

                return list;*/
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
     * @return SearchRequestBuilder
     */
    public SearchRequestBuilder getBasicBuilderWithException(PlainSelect plainSelect, Client client) {

        final FromItem item = plainSelect.getFromItem();
        final List<SelectItem> selectItems = plainSelect.getSelectItems();

        /**
         * 包含groupby
         */
        final List<Expression> expList = plainSelect.getGroupByColumnReferences() == null ? new ArrayList<Expression>() :
                plainSelect.getGroupByColumnReferences();

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
        final String indexAndTypes = item.toString();
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
        final String[] index = new String[indexSet.size()];
        final List<String> indexList = Lists.newArrayList(indexSet);
        for (int i = 0; i < indexList.size(); i++) {
            index[i] = indexList.get(i);
        }
        final SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);


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
                searchRequestBuilder.addAggregation(termsBuilder);
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
            final String[] type = new String[typeSet.size()];
            final List<String> typeList = Lists.newArrayList(typeSet);
            for (int i = 0; i < typeList.size(); i++) {
                type[i] = typeList.get(i);
            }
            searchRequestBuilder.setTypes(type);
        }

        /**
         * 如果有明确的查询的列名
         */
        if (!(selectItems.get(0).toString().contains("*"))) {

            //final String[] returnColumns = new String[selectItems.size()];
            final ArrayList<String> returnColumns = new ArrayList<>();
            for (SelectItem selectItem : selectItems) {
                final SelectExpressionItem seItem = (SelectExpressionItem) selectItem;
                //判断是否包含函数(sql标准函数)
                final Expression ee = seItem.getExpression();
                if (ee instanceof Function) {
                    final Function fun = (Function) ee;
                    final String funName = fun.getName().toLowerCase().trim();
                    if (funName.equals("max") || Objects.equals(funName, "min") || Objects.equals(funName, "sum") ||
                            Objects.equals(funName, "count") || Objects.equals(funName, "avg")) {
                        searchRequestBuilder.addAggregation(getAbstractAggregationBuilderWithSqlFun(fun));
                    } else {
                        if (funName.equals("highlight")) {
                            final List<String> colums = Splitter.on(",").splitToList(String.valueOf(((Function) ee).getParameters()).replace(")", "").replace("(", "").trim());
                            for (String column : colums) {
                                //设置高亮显示
                                searchRequestBuilder.addHighlightedField(column.trim());
                                returnColumns.add(column);
                            }
                            searchRequestBuilder.setQuery(generateQueryBuilder(colums));
                            searchRequestBuilder.setHighlighterPreTags("<span style='color:red'>");
                            searchRequestBuilder.setHighlighterPostTags("</span>");
                        } else {
                            //普通的列名非函数
                            returnColumns.add(String.valueOf(fun.getParameters()).replace(")", "").replace("(", ""));
                        }
                    }
                } else {
                    //普通的列名非函数
                    returnColumns.add(seItem.toString());
                }
            }
            if (returnColumns.size() > 0) {
                final String[] columns = new String[returnColumns.size()];
                for (int i = 0; i < returnColumns.size(); i++) {
                    columns[i] = returnColumns.get(i);
                }
                searchRequestBuilder.setFetchSource(columns, null);
            }
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
     * 返回querybuilder
     *
     * @param columns
     * @return queryBuilder
     */
    public QueryStringQueryBuilder generateQueryBuilder(List<String> columns) {
        for (String column : columns) {
            queryBuilder = queryBuilder.field(column);
        }
        return queryBuilder;
    }

    /**
     * 递归生成filter
     *
     * @param ex            表达式
     * @param filterBuilder 过滤器
     * @return FilterBuilder
     */
    private FilterBuilder generateFilter(Expression ex, FilterBuilder filterBuilder) {
        //去除括号类型表达式的括号
        ex = getExpressionWithoutParenthesis(ex);

        //自定义操作
        if (ex instanceof RegExpMatchOperator) {
            //操作符
            final String oper = ((RegExpMatchOperator) ex).getStringExpression();
            final String[] arr = ex.toString().split(oper);
            final ObjectExpression objectExpression = new ObjectExpression();
            objectExpression.setColumnname(arr[0].trim());
            objectExpression.setExp(oper);
            objectExpression.setValue(arr[1].trim());
            return getFilterBuilderWithOperators(objectExpression);
        }
        // id in ()语句
        if (ex instanceof InExpression) {
            final InExpression inExpression = (InExpression) ex;
            final ObjectExpression objectExpression = new ObjectExpression();
            objectExpression.setColumnname(inExpression.getLeftExpression().toString());
            objectExpression.setExp("IN");
            objectExpression.setValue(inExpression.getRightItemsList());
            return getFilterBuilderWithOperators(objectExpression);

        }
        final BinaryExpression be = (BinaryExpression) ex;
        if (ex == null) {
            //没有where条件,查询全部
            return matchAllFilter();
        } else if (be.getLeftExpression() instanceof Column || be.getRightExpression() instanceof Column) {
            //如果是表达式阶段,如 a=1,则直接返回
            return getFilterBuilderWithOperators(processExpression(be));

        } else if (!(be.getLeftExpression() instanceof AndExpression || be.getLeftExpression() instanceof OrExpression ||
                be.getLeftExpression() instanceof Parenthesis || be.getLeftExpression() instanceof InExpression) &&
                (!(be.getRightExpression() instanceof AndExpression || be.getRightExpression() instanceof OrExpression ||
                        be.getRightExpression() instanceof Parenthesis || be.getRightExpression() instanceof InExpression))) {
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


    /**
     * 根据sql函数返回Aggregation
     *
     * @param function 函数对象
     * @return AbstractAggregationBuilder
     */
    public AbstractAggregationBuilder getAbstractAggregationBuilderWithSqlFun(Function function) {
        //函数参数
        String param = function.getParameters().toString().substring(1,
                function.getParameters().toString().length() - 1).trim();
        switch (function.getName().toLowerCase().trim()) {
            case "count":
                return AggregationBuilders.count(param + "_count")
                        .field(param);
            case "max":
                return AggregationBuilders.max(param + "_max")
                        .field(param);
            case "min":
                return AggregationBuilders.min(param + "_min")
                        .field(param);
            case "sum":
                return AggregationBuilders.sum(param + "_sum")
                        .field(param);
            case "avg":
                return AggregationBuilders.avg(param + "_avg")
                        .field(param);
            default:
                return null;
        }
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
        if (queryBuilder != null) {
            queryBuilder = null;
        }
        switch (objectExpression.exp) {
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
            case "LIKE":
                //return FilterBuilders.termFilter(objectExpression.columnname, objectExpression.getValue());
                return FilterBuilders.queryFilter(QueryBuilders.termQuery(objectExpression.columnname, objectExpression.value));
            //type暂时没传
            case "IN":
                return FilterBuilders.idsFilter(null).addIds(objectExpression.value.toString().replace("(", "").replace(")", "").replace("\'", ""));
            //约等于
            case "~":
                queryBuilder = new QueryStringQueryBuilder(objectExpression.value.toString().substring(1,
                        objectExpression.value.toString().length() - 1));
                return FilterBuilders.queryFilter(
                        queryStringQuery(objectExpression.value.toString().substring(1,
                                objectExpression.value.toString().length() - 1)).defaultField(objectExpression.columnname));
            case "=":
                queryBuilder = new QueryStringQueryBuilder(objectExpression.value.toString());
                if (objectExpression.columnname.equals("missing")) {
                    return FilterBuilders.missingFilter(objectExpression.value.toString());

                } else if (objectExpression.columnname.equals("exist")) {
                    return FilterBuilders.existsFilter(objectExpression.value.toString());
                }
                return FilterBuilders.termFilter(objectExpression.columnname, objectExpression.value);
            default:
                return null;
        }
    }
}


