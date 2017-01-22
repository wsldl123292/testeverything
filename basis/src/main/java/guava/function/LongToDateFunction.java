package guava.function;

import com.google.common.base.Function;

import java.util.Date;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 15:17
 */
public class LongToDateFunction implements Function<Long,Date> {
    @Override
    public Date apply(Long input) {
        Date date = new Date();
        date.setTime(input);
        return date;
    }
}
