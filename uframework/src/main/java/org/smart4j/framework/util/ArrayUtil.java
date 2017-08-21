package org.smart4j.framework.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 23:05
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }
}
