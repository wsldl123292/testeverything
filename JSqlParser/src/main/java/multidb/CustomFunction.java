package multidb;

import com.google.common.base.Splitter;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/7 10:13
 */
public class CustomFunction {

    /**
     * 函数体
     */
    private Function function;

    /**
     * 结果集
     */
    private ArrayList<String> list;

    /**
     * 返回单个结果
     */
    private Object object;


    /**
     * 返回多个结果
     */
    private List<Object> objects;

    /**
     * 构造函数
     *
     * @param function 函数体
     * @param list     结果集
     */
    public CustomFunction(Function function, ArrayList<String> list) {
        this.function = function;
        this.list = list;
    }

    /**
     * 函数执行
     */
    public void execute() {
        final String funName = function.getName();
        final List<Expression> expressions = function.getParameters().getExpressions();
        final List<String> params = new ArrayList<>();
        for (Expression expression : expressions) {
            params.add(expression.toString());
        }
        final UDF udf = new UDF();
        udf.setfName(funName);
        final Connection connection = MySqlUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("select * from defined_functions where f_name = ?");
            preparedStatement.setString(1, funName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                udf.setfContent(resultSet.getString("f_content"));
                udf.setfParams(Splitter.on(";").withKeyValueSeparator(",").split(resultSet.getString("f_params")));
                udf.setfReturn(Splitter.on(";").withKeyValueSeparator(",").split(resultSet.getString("f_return")));
                udf.setfType(resultSet.getInt("f_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlUtil.close(connection, preparedStatement, null, resultSet);
        }

        if (udf.getfType() == 1) {
            final Binding binding = new Binding();

            //遍历参数
            for (String param : udf.getfParams().keySet()) {
                if (udf.getfParams().get(param).equals("list")) {
                    binding.setVariable(param, list);
                } else {
                    binding.setVariable(param, params.get(0));
                }
            }

            final GroovyShell gs = new GroovyShell(binding);

            final Object object = gs.evaluate("import com.alibaba.fastjson.JSON; " +
                    "resultList;column;\nprint(column);print(resultList);" +
                    "        def jsonObject;\n" +
                    "        resultList.each {\n" +
                    "            jsonObject = JSON.parseObject(it);\n" +
                    "            jsonObject.put(column, \"test2\");\n" +
                    "        };\n" +
                    "        return jsonObject;");
            System.out.println("hahahahahahah......." + object.toString());

        } else if (udf.getfType() == 2) {

        } else if (udf.getfType() == 3) {

        }

    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
