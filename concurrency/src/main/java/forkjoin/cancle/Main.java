package forkjoin.cancle;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 22:58
 */
public class Main {

    public static void main(String[] args) {
        ArrayGenerator generator = new ArrayGenerator();
        int array[] = generator.generateArray(1000);
        TaskManager manager = new TaskManager();
        ForkJoinPool pool = new ForkJoinPool();

        SearchNumberTask task = new SearchNumberTask(0, 1000, 5, manager, array);

        pool.execute(task);

        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: The program has finished\n");
    }
}
