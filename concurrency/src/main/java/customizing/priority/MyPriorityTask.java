package customizing.priority;

import synchronization.condition.Producer;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-11 15:27
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {

    private int priority;
    private String name;

    public MyPriorityTask(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        } else if (this.getPriority() > o.getPriority()) {
            return -1;
        }
        return 0;
    }

    @Override
    public void run() {

        System.out.printf("MyPriorityTask: %s Priority : %d\n",name, priority);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
