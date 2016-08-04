package threadexecutor;

import sun.text.resources.cldr.ia.FormatData_ia;
import threadsyncutilities.cyclicbarrier.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 15:11
 */
public class Cmain {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        List<Future<Integer>> futureList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculator ca = new FactorialCalculator(number);
            Future<Integer> future = executor.submit(ca);

            futureList.add(future);
        }


        do {
            System.out.printf("Main: Number of Completed Tasks: %d\n",executor.getCompletedTaskCount());
            for (int i = 0; i < futureList.size(); i++) {
                Future<Integer> future = futureList.get(i);
                System.out.printf("Main: Task %d: %s\n",i,future.isDone());
            }

            try {
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (executor.getCompletedTaskCount() < futureList.size());


        System.out.printf("Main: Results\n");
        for (int i = 0; i < futureList.size(); i++) {
            Future<Integer> future = futureList.get(i);
            Integer number = null;
            try {
                number = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            System.out.printf("Main: Task %d: %d\n",i,number);
        }


        executor.shutdown();
    }

}
