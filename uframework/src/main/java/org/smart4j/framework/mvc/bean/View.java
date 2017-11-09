package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.core.bean.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/11/9 16:03
 */
public class View extends BaseBean {
    private String path;
    private Map<String, Object> data;

    public View(String path) {
        this.path = path;
        data = new HashMap<>();
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
