package threadmanagement.daemon;


import java.nio.channels.Pipe;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 19:42
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writerTask = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writerTask);
            thread.start();
            System.out.println(thread.getPriority());
        }

        ClearnerTask cleanerTask = new ClearnerTask(deque);
        Thread thread = new Thread(cleanerTask);
        thread.setDaemon(true);
        //thread.setPriority(1);
        thread.start();
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(deque.size());
    }
}
