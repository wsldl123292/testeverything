package struct.blocking;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 15:48
 */
public class BMain {

    public static void main(String[] args) throws InterruptedException {
//        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
//        Client client = new Client(list);
//        Thread thread = new Thread(client);
//        thread.start();
//
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 3; j++) {
//                String request = list.take();
//                System.out.printf("Main: Request: %s at %s. Size: %d\n",request,new Date(),list.size());
//            }
//
//            TimeUnit.MILLISECONDS.sleep(300);
//        }
//
//        System.out.printf("Main: End of the program.\n");


        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task(i,queue);
            threads[i] = new Thread(task);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.printf("Main: Queue Size: %d\n",queue.size());

        for (int i = 0; i < threads.length*1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n",event.getThread(),event.getPriority());
        }


        System.out.printf("Main: Queue Size: %d\n",queue.size());
        System.out.printf("Main: End of program.\n");

    }
}
