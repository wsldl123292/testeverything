package org.smart4j.framework.core;

import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

public class ConfigHelper {


    /**
     * 属性文件对象
     */
    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);


    /**
     * 获取 String 类型的属性值
     */
    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(String key, String defaultValue) {
        return PropsUtil.getString(configProps, key, defaultValue);
    }

    /**
     * 获取 int 类型的属性值
     */
    public static int getInt(String key) {
        return PropsUtil.getNumber(configProps, key);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static int getInt(String key, int defaultValue) {
        return PropsUtil.getNumber(configProps, key, defaultValue);
    }
}
