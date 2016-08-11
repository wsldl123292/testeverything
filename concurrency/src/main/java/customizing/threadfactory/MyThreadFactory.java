package customizing.threadfactory;

import java.util.concurrent.ThreadFactory;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-11 17:25
 */
public class MyThreadFactory implements ThreadFactory {

    private int counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread myThread = new MyThread(r, prefix + "-" + counter);
        counter++;
        return myThread;
    }
}
