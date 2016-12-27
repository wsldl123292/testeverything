package splitter;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/5/31 12:17
 */
public class SplitterTest {

    @Test
    public void testSplitter(){
        String startSring = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");

        Splitter.MapSplitter mapSplitter  = Splitter.on("#").withKeyValueSeparator("=");
        Map<String,String> map = mapSplitter.split(startSring);

        assertThat(map, is(testMap));
    }

    @Test
    public void testSplitPattern(){
        String pattern = "\\d+";
        String text = "foo123bar45678baz";
        String[] expected = new String[]{"foo","bar","baz"};
        Iterable<String> values = Splitter.on(Pattern.compile(pattern)).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value,is(expected[index++]));
        }
    }

    @Test
    public void testSplitTrimResults(){
        String delimiter = "&";
        String text = "foo   &  bar&   baz  ";
        String[] expected = new String[]{"foo","bar","baz"};
        Iterable<String> values = Splitter.on(delimiter).trimResults().split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value,is(expected[index++]));
        }
    }

    @Test
    public void testSplitOnCharacterOmitMissing(){
        char delimiter = '|';
        String text = "foo|bar|||baz";
        String[] expected = new String[]{"foo","bar","baz"};
        Iterable<String> values = Splitter.on(delimiter).omitEmptyStrings().split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value,is(expected[index++]));
        }
    }
}
