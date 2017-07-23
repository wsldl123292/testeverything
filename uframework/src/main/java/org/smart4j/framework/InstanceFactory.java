package org.smart4j.framework;

import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InstanceFactory {

    /**
     * 用于缓存对应的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * ClassScanner
     */
    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";

    /**
     * DataAccessor
     */
    private static final String DATA_ACCESSOR = "smart.framework.custom.data_accessor";

    /**
     * HandlerMapping
     */
    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    /**
     * HandlerInvoker
     */
    private static final String HANDLER_INVOKER = "smart.framework.custom.handler_invoker";

    /**
     * HandlerExceptionResolver
     */
    private static final String HANDLER_EXCEPTION_RESOLVER = "smart.framework.custom.handler_exception_resolver";

    /**
     * ViewResolver
     */
    private static final String VIEW_RESOLVER = "smart.framework.custom.view_resolver";

    /**
     * 获取 ClassScanner
     */
    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // 若缓存中存在对应的实例，则返回该实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // 从配置文件中获取相应的接口实现类配置
        String implClassName = ConfigHelper.getString(cacheKey);
        // 若实现类配置不存在，则使用默认实现类
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        // 通过反射创建该实现类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);
        // 若该实例不为空，则将其放入缓存
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        // 返回该实例
        return instance;
    }
}
