package org.smart4j.framework.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 15:30
 */
public class CollectionUtil {

    /**
     * 判断集合是否非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
