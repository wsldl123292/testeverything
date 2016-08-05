package forkjoin.sync;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 20:01
 */
public class Main {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FolderProcesser system = new FolderProcesser("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/synchronization", "java");
        FolderProcesser system1 = new FolderProcesser("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/threadmanagement", "java");
        FolderProcesser system2 = new FolderProcesser("/Users/ldl/Documents/develop/project/github/testeverything/concurrency/src/main/java/threadsyncutilities", "java");

        pool.execute(system);
        pool.execute(system1);
        pool.execute(system2);


        do {
            System.out.println("************************************************");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.println("************************************************");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!system.isDone() || !system1.isDone() || !system2.isDone());

        pool.shutdown();

        List<String> results = system.join();
        System.out.printf("System: %d files found.\n",results.size());
        system1.join();
        System.out.printf("System1: %d files found.\n",results.size());
        system2.join();
        System.out.printf("System2: %d files found.\n",results.size());
    }
}
