package guava.function;

import com.google.common.base.Function;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 15:14
 */
public class DateFormatFunction implements Function<Date,String> {

    private String dateFormat;

    public DateFormatFunction(String dateFormat){
        this.dateFormat = dateFormat;
    }
    @Override
    public String apply(Date input) {
        return new SimpleDateFormat(dateFormat).format(input);
    }
}
