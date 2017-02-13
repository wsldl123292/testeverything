package synchronization.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 16:28
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock();

    public void printJob(Object document){
        queueLock.lock();
        try {
            long duration = (long) (Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":PrintQueue: Printing a Job during "+(duration
                    /1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }



        /*queueLock.lock();
        System.out.println("2222222");
        long duration = (long) (Math.random()*10000);
        System.out.println(Thread.currentThread().getName()+":PrintQueue: Printing a Job during "+(duration
                /1000)+" seconds");

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }*/

    }
}
