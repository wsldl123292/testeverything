package synchronization.synchroniz;

import java.util.Date;
import java.util.LinkedList;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 13:01
 */
public class EventStorage {

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    private int maxSize;
    private LinkedList<Date> storage;

    public LinkedList<Date> getStorage() {
        return storage;
    }

    public void setStorage(LinkedList<Date> storage) {
        this.storage = storage;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void set() throws InterruptedException {
        while (storage.size() == maxSize){
            wait();
        }

        storage.add(new Date());
        System.out.printf("Set: %d\n",storage.size());
        notifyAll();
    }

    public synchronized void get() throws InterruptedException {
        while (storage.size() == 0){
            wait();
        }

        System.out.printf("Get: %d: %s",storage.size(),storage.poll());
        notifyAll();
    }
}
