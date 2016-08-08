package struct.nonblocking;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 15:25
 */
public class NonMain {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> list = new ConcurrentLinkedDeque<>();

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            AddTask task = new AddTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }

        System.out.printf("Main: %d AddTask threads have been launched\n",threads.length);


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n",list.size());


        for (int i = 0; i < threads.length; i++) {
            PollTask task = new PollTask(list);
            threads[i] = new Thread(task);
            threads[i].start();
        }
        System.out.printf("Main: %d PollTask threads have been launched\n",threads.length);


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Size of the List: %d\n",list.size());

    }
}
