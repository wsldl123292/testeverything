package threadmanagement.group;

import com.sun.xml.internal.fastinfoset.stax.factory.StAXOutputFactory;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-31 12:43
 */
public class GroupMain {
    public static void main(String[] args) throws InterruptedException {
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        ThreadGroup threadGroup = new ThreadGroup("Searcher");

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(threadGroup,searchTask);
            thread.start();
            TimeUnit.SECONDS.sleep(1);
        }


        System.out.printf("Number of Threads: %d\n",threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();


        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt();
    }

    private static void waitFinish(ThreadGroup threadGroup) throws InterruptedException {
        System.out.println("ddddddd: "+ threadGroup.activeCount());
        while (threadGroup.activeCount() > 3){
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
