package collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/9 10:14
 */
public class BiMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBiMapSameValueDifferentKey() throws Exception {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.put("2","Tom");
    }

    /**
     * 不同键相同值时，前一个键取消
     * @throws Exception
     */
    @Test
    public void testBiMapForcePut() throws Exception {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.forcePut("2","Tom");
        assertThat(biMap.containsKey("1"),is(false));
        assertThat(biMap.containsKey("2"),is(true));
    }

    /**
     * 键值互换
     * @throws Exception
     */
    @Test
    public void testBiMapInverse() throws Exception {
        BiMap<String,String> biMap = HashBiMap.create();
        biMap.put("1","Tom");
        biMap.put("2","Harry");
        assertThat(biMap.get("1"),is("Tom"));
        assertThat(biMap.get("2"),is("Harry"));
        BiMap<String,String> inverseMap = biMap.inverse();
        assertThat(inverseMap.get("Tom"),is("1"));
        assertThat(inverseMap.get("Harry"),is("2"));
    }
}
