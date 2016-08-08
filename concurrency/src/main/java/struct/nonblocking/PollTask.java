package struct.nonblocking;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 15:23
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
