package struct.blocking;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 16:09
 */
public class Task implements Runnable {

    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id,i);
            queue.add(event);
        }
    }
}
