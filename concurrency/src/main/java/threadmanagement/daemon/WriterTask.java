package threadmanagement.daemon;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 19:29
 */
class WriterTask implements Runnable {
    private Deque<Event> deque;

    WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        //System.out.println("dddddd");
        for (int i = 1; i < 3; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("线程 %s 已经生成一个事件", Thread.currentThread().getId()));
            deque.addFirst(event);
            //System.out.println(deque.size());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
