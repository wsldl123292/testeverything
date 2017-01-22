package guava.function;

import com.google.common.base.Function;

import java.util.Date;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 15:13
 */
public class StringToDateFunction implements Function<String,Function<Date,String>> {
    @Override
    public Function<Date, String> apply(String input) {
        return new DateFormatFunction(input);
    }
}
