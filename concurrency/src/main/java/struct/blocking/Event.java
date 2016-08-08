package struct.blocking;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 16:08
 */
public class Event implements Comparable<Event> {

    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {

        if (this.priority > o.getPriority()) {
            return -1;
        } else if (this.priority < o.getPriority()) {
            return 1;
        }
        return 0;
    }
}
