package struct.delay;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 16:31
 */
public class Event implements Delayed {

    private Date startDate;

    public Event(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Date now = new Date();
        long diff = startDate.getTime()-now.getTime();
        return unit.convert(diff,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long result = this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS);

        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        }
        return 0;
    }
}
