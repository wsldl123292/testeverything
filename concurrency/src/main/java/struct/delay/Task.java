package struct.delay;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 16:35
 */
public class Task implements Runnable {

    private DelayQueue<Event> queue;

    private int id;

    public Task(DelayQueue<Event> queue, int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + (id * 1000));
        System.out.printf("Thread %s: %s\n", id, delay);

        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
