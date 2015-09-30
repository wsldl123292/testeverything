package java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:23
 */
public class SparkJobConfig {
    private Map<String, Object> configs = new HashMap<String, Object>();

    void putConfigItem(String key, Object value) {
        this.configs.put(key, value);
    }

    /**
     * Gets all the configuration items.
     *
     * @return a map holding the key-value pairs of configuration items
     */
    public Map<String, Object> getConfigs() {
        return new HashMap<>(this.configs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("SparkJobConfig\n{\n");
        final Set<Map.Entry<String, Object>> items = configs.entrySet();
        for (Map.Entry<String, Object> item : items) {
            buff.append(" ").append(item.getKey()).append(": ")
                    .append(item.getValue().toString()).append("\n");
        }
        buff.append("}");
        return buff.toString();
    }
}
