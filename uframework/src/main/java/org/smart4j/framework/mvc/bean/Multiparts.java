package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.core.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 封装批量文件上传对象相关属性
 * @作者 liudelin
 * @日期 2017/9/19 11:04
 */
public class Multiparts extends BaseBean {
    private List<Multipart> multipartList = new ArrayList<>();

    public Multiparts(List<Multipart> multipartList) {
        this.multipartList = multipartList;
    }

    public int size() {
        return multipartList.size();
    }

    public List<Multipart> getAll() {
        return multipartList;
    }

    public Multipart getOne() {
        return size() == 1 ? multipartList.get(0) : null;
    }
}
