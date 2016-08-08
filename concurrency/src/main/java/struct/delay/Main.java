package struct.delay;


import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 16:37
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Event> delayQueue = new DelayQueue<>();
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(delayQueue, i + 1);
            threads[i] = new Thread(task);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        do {
            int counter = 0;
            Event event;
            do {
                event = delayQueue.poll();
                if (event != null) {
                    counter++;
                }
            }
            while (event != null);
            System.out.printf("At %s you have read %d events\n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (delayQueue.size() > 0);
    }
}
