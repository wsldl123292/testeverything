package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @描述 属性文件操作工具类
 * @作者 liudelin
 * @日期 2017/7/14 15:00
 */
public class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String propsPath) {
        Properties properties = new Properties();
        InputStream inputStream;
        if (StringUtil.isEmpty(propsPath)) {
            throw new IllegalArgumentException();
        }
        String suffix = ".properties";
        if (propsPath.lastIndexOf(suffix) == -1) {
            propsPath += suffix;
        }
        inputStream = ClassUtil.getClassLoader().getResourceAsStream(propsPath);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                logger.error("加载属性文件出错！", e);
                throw new RuntimeException();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("释放资源出错！", e);
                }
            }
        }

        return properties;
    }

    public static String getString(Properties configProps, String key) {
        String value = "";
        if (configProps.containsKey(key)) {
            value = configProps.getProperty(key);
        }
        return value;

    }

    public static String getString(Properties configProps, String key, String defaultValue) {

        String value = defaultValue;
        if (configProps.containsKey(key)) {
            value = configProps.getProperty(key);
        }
        return value;
    }

    public static int getNumber(Properties configProps, String key) {
        int value = 0;
        if (configProps.containsKey(key)) {
            value = CastUtil.castInt(configProps.getProperty(key));
        }
        return value;
    }

    public static int getNumber(Properties configProps, String key, int defaultValue) {

        int value = defaultValue;
        if (configProps.containsKey(key)) {
            value = CastUtil.castInt(configProps.getProperty(key));
        }
        return value;
    }
}
