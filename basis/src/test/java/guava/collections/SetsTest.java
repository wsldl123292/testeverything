package guava.collections;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/9 9:50
 */
public class SetsTest {

    /**
     * 前一个set中有，第二个set中没有的元素
     */
    @Test
    public void testDifference(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("2","3","4");
        assertThat(Sets.difference(s1,s2).contains("1"), is(true));
    }

    /**
     * 两个set中不同时存在的元素
     */
    @Test
    public void testSymmetricDifference(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("2","3","4");
        Sets.SetView<String> setView = Sets.symmetricDifference(s1,s2);
        assertThat(setView.contains("1"), is(true));
        assertThat(setView.contains("4"), is(true));
    }

    /**
     * 相同位置的相同元素
     */
    @Test
    public void testIntersection(){
        Set<String> s1 = Sets.newHashSet("3","2","4");
        Set<String> s2 = Sets.newHashSet("3","2","1");
        Sets.SetView<String> sv = Sets.intersection(s1,s2);
        assertThat(sv.size()==2 && sv.contains("2") &&
                sv.contains("3"),is(true));
    }

    @Test
    public void testUnion(){
        Set<String> s1 = Sets.newHashSet("1","2","3");
        Set<String> s2 = Sets.newHashSet("3","2","4");
        Sets.SetView<String> sv = Sets.union(s1,s2);
        assertThat(sv.size()==4 &&
                sv.contains("2") &&
                sv.contains("3") &&
                sv.contains("4") &&
                sv.contains("1"),is(true));
    }

}
