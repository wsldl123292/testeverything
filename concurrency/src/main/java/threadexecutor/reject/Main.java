package threadexecutor.reject;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 14:54
 */
public class Main {

    public static void main(String[] args) {
        RejectedTaskController rejectedTaskController = new RejectedTaskController();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        executor.setRejectedExecutionHandler(rejectedTaskController);

        System.out.printf("Main: Starting.\n");

        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task "+i);
            executor.submit(task);
        }


        System.out.printf("Main: Shutting down the Executor.\n");

        executor.shutdown();

        System.out.printf("Main: Sending another Task.\n");
        Task task = new Task("RejectedTask");
        executor.submit(task);

        System.out.println("Main: End");
    }
}
