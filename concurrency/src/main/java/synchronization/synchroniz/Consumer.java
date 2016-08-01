package synchronization.synchroniz;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 13:07
 */
public class Consumer implements Runnable {

    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                eventStorage.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
