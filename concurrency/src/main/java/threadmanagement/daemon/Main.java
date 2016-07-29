package threadmanagement.daemon;


import java.nio.channels.Pipe;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 19:42
 */
public class Main {
    public static void main(String[] args) {
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
        thread.start();
        System.out.println(thread.getPriority());
        System.out.println(thread.isDaemon());
    }
}
