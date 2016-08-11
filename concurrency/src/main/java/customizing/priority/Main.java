package customizing.priority;

import java.util.concurrent.*;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-11 15:30
 */
public class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask(i, "Task " + i);
            executor.execute(task);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask(i, "Task " + i);
            executor.execute(task);
        }

        executor.shutdown();


        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Main: End of the program.");

    }
}
