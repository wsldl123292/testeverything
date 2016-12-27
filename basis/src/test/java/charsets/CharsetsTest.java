package charsets;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/5/31 13:23
 */
public class CharsetsTest {

    @Test
    public void testCharsets(){
        byte[] bytes = null;
        try{
            bytes = "foobarbaz".getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            //This really can't happen UTF-8 must be supported
        }

        byte[] bytes2 = "foobarbaz".getBytes(Charsets.UTF_8);

        assertThat(bytes,is(bytes2));
    }

    @Test
    public void testBuildString(){
        StringBuilder builder = new StringBuilder("foo");
        char c = 'x';
        for(int i=0; i<3; i++){
            builder.append(c);
        }

        String s = Strings.padEnd("foo",6,'x');

        assertThat(builder.toString(),is(s));
    }

    @Test
    public void testNullToEmpty(){
        String s = null;
        assertThat("",is(Strings.nullToEmpty(s)));
    }
}
