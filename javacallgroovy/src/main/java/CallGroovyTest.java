import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/19 15:03
 */
public class CallGroovyTest {

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ResourceException, ScriptException {
        /*ClassLoader parent = ClassLoader.getSystemClassLoader();

        GroovyClassLoader loader = new GroovyClassLoader(parent);

        Class groovyClass = loader.parseClass(new File("D:\\GroovyDemo.groovy"));

        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
        Object[] param = {123,321};

        int res = (int) groovyObject.invokeMethod("add", param);

        System.out.println("res="+res);*/
        Binding binding = new Binding();

        ArrayList<String> list = new ArrayList<>();
        list.add("{\"name\",:\"test\"}");
        binding.setVariable("list", list);
        binding.setVariable("column","name");
        GroovyShell gs = new GroovyShell(binding);

        final Object object = gs.evaluate("import com.alibaba.fastjson.JSON; " +
                "list;column;\nprint(column);print(list);" +
                "        def jsonObject;\n" +
                "        list.each {\n" +
                "            jsonObject = JSON.parseObject(it);\n" +
                "            jsonObject.put(column, \"test2\");\n" +
                "        };\n" +
                "        return jsonObject;");
        System.out.println(object);
        //System.out.println(value.getClass());

        /*GroovyScriptEngine gse = new GroovyScriptEngine("");

        Binding binding = new Binding();

        binding.setVariable("a", 10);

        gse.run("if(a>0){print(\"a>0\")} else{print(\"a<0\")}",binding);*/

        /*Binding binding = new Binding();

        binding.setVariable("a", 5);

        GroovyShell gs = new GroovyShell();

        Script script = gs.parse("if(a>0){print(\"a>0\")} else{print(\"a<0\")}");
        script.setBinding(binding);

        script.run();*/

    }
}
