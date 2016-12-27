package joiner;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.CharSink;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/5/29 16:07
 */
public class JoinerTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testJoinString() throws Exception {
        List<String> values = Lists.newArrayList("a","b","c");
        String buildString = Joiner.on("|").join(values);
        assertEquals(buildString, "a|b|c");

    }

    @Test (expected = NullPointerException.class)
    public void testBuildStringNotSkipNull(){

        ArrayList<String> values = Lists.newArrayList("a",null,"c");
        Joiner.on("|").join(values);
        fail("Should not get here");
    }

    @Test
    public void testBuildStringSkipNull(){

        ArrayList<String> values = Lists.newArrayList("a",null,"c");
        String buildString = Joiner.on("|").skipNulls().join(values);
        assertEquals(buildString, "a|c");
    }

    @Test
    public void testBuildStringReplaceNullWithString(){

        ArrayList<String> values = Lists.newArrayList("a",null,"c");
        String buildString = Joiner.on("|").useForNull("not value").join(values);
        assertEquals(buildString, "a|not value|c");
    }

    @Test
    public void testBuildString(){
        List<String> values = Lists.newArrayList("a","b","c");
        StringBuilder builder = new StringBuilder();
        Joiner.on("|").appendTo(builder,values);
        assertEquals(builder.toString(), "a|b|c");
    }

    @Test
    public void testJoinFileWriter() throws Exception{
        File tempFile = new File("testTempFile.txt");
        tempFile.deleteOnExit();
        CharSink charSink = Files.asCharSink(tempFile, Charsets.UTF_8);
        Writer writer = charSink.openStream();
        String[] values = new String[]{"foo", "bar","baz"};
        Joiner.on("|").appendTo(writer,values);
        writer.flush();
        writer.close();
        String fromFileString = Files.toString(tempFile, Charsets.UTF_8);
        assertThat(fromFileString, is("foo|bar|baz"));

    }

    @Test
     public void testMapJoiner() {
        String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String,String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C","Redskins");
        testMap.put("New York City","Giants");
        testMap.put("Philadelphia","Eagles");
        testMap.put("Dallas","Cowboys");
        String returnedString = Joiner.on("#").
                withKeyValueSeparator("=").join(testMap);
        assertThat(returnedString,is(expectedString));
    }
}
