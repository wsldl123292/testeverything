package threadmanagement.daemon;


import java.util.ArrayDeque;
import java.util.Date;
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
//        WriterTask writerTask = new WriterTask(deque);
//        for (int i = 0; i < 3; i++) {
//            Thread thread = new Thread(writerTask);
//            thread.start();
//            System.out.println(thread.getPriority());
//        }


        ClearnerTask cleanerTask = new ClearnerTask(deque);
        Thread thread = new Thread(cleanerTask);
        thread.setDaemon(true);
        //thread.setPriority(1);
        thread.start();
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());

        for (int i = 0; i < 3; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("线程 %s 已经生成一个事件", Thread.currentThread().getId()));
            deque.addFirst(event);
            //System.out.println(deque.size());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(deque.size());
        }


    }
}
