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

    public static Properties loadProperties(String propsPath) {
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
}
