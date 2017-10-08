package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.core.bean.BaseBean;
import org.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * @描述 封装请求参数
 * @作者 liudelin
 * @日期 2017/9/19 14:55
 */
public class Params extends BaseBean {

    private final Map<String, Object> fieldMap;

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public String getString(String name) {
        return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(get(name));
    }

    private Object get(String name) {
        return fieldMap.get(name);
    }
}
