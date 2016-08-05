package threadexecutor.reject;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 14:46
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Task " + name + ": Starting");

        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("Task %s: Waiting for %d seconds\n", name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Task %s: Ending\n", name);
    }

    @Override
    public String toString() {
        return name;
    }
}
