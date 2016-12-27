package algs4.bsq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/6/7 17:48
 */
public class FixedCapacityStackTest {

    private FixedCapacityStack<String> fixedCapacityStack;
    @Before
    public void setUp() throws Exception {
        fixedCapacityStack = new FixedCapacityStack<>(5);
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertThat(fixedCapacityStack.isEmpty(),is(true));
    }

    @Test
    public void testSize() throws Exception {
        fixedCapacityStack.push("hello");
        assertThat(fixedCapacityStack.size(), is(1));
    }

    @Test
    public void testPop() throws Exception {
        fixedCapacityStack.push("hello");
        fixedCapacityStack.push("world");
        assertThat(fixedCapacityStack.pop(),is("world"));
    }
}