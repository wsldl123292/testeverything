package threadmanagement.daemon;


import java.util.Date;
import java.util.Deque;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 19:35
 */
class ClearnerTask implements Runnable {

    private Deque<Event> deque;

    ClearnerTask(Deque<Event> deque) {
        this.deque = deque;
    }


    @Override
    public void run() {
        System.out.println("kaishi");
        while (true) {

            Date date = new Date();
            clean(date);
        }
    }


    private void clean(Date date) {
        long difference;
        boolean delete;
        System.out.println(deque.size());
        if (deque.size() == 0) {
            return;
        }

        delete = false;
        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            //System.out.println(difference);
            //System.out.println(deque.size());
            //System.out.println(delete);
            if (difference > 2000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 2000);
        if (delete) {
            System.out.printf("Cleaner: Size of queue: %s\n", deque.size());
        }
    }
}
