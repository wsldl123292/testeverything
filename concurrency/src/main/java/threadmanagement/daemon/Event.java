package threadmanagement.daemon;

import java.util.Date;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 19:20
 */
class Event {

    private Date date;

    private String event;


    Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    String getEvent() {
        return event;
    }

    void setEvent(String event) {
        this.event = event;
    }
}
