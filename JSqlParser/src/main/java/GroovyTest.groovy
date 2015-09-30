/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/7 17:23
 */

import com.alibaba.fastjson.JSON

class GroovyTest {

    public static void main(String[] args) {
        def list;
        def column;
        def jsonObject;
        list.each {
            jsonObject = JSON.parseObject(it);
            jsonObject.put(column, "test2");
        };
    }
}
