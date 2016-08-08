package struct.nonblocking;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-07 22:09
 */
public class AddTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public AddTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10000; i++) {
            list.add(name + ": Element " + i);
        }
    }
}
