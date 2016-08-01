package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 13:08
 */
public class PCMain {

    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        Thread p = new Thread(producer);
        Thread c = new Thread(consumer);

        p.start();
        c.start();
    }
}
