package CharMatcher;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/5/31 14:00
 */
public class CharMatcherTest {
    @Test
    public void testRemoveWhiteSpace(){
        String tabsAndSpaces = "String   with spaces and tabs";
        String expected = "String with spaces and tabs";
        String scrubbed = CharMatcher.BREAKING_WHITESPACE.
                collapseFrom(tabsAndSpaces,' ');
        assertThat(scrubbed,is(expected));
    }

    @Test
    public void testTrimRemoveWhiteSpace(){
        String tabsAndSpaces = "  String   with spaces and tabs";
        String expected = "String with spaces and tabs";
        String scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces,' ');
        assertThat(scrubbed,is(expected));
    }

    @Test
    public void testRemoveLinebreaks(){
        String stringWithLinebreaks = "This is an example\n"+
                "of a String with linebreaks\n"+
                "we want on one line";
        String expected = "This is an example of a String with linebreaks we want on one line";
        String scrubbed = CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLinebreaks,' ');
        System.out.println(scrubbed);
        assertThat(scrubbed,is(expected));
    }
    @Test
    public void testRetainFrom(){
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);
        System.out.println(retained);
        assertThat(expected,is(retained));
    }

    @Test
    public void testCombineMatchers(){
        CharMatcher cm = CharMatcher.inRange('A','E');
        assertThat(cm.retainFrom("aaaABbbccCdddDEeee"),is("ABCDE"));
    }
}
