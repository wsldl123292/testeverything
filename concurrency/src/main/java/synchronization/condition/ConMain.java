package synchronization.condition;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 14:12
 */
public class ConMain {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(mock, buffer);
        Thread producerThread = new Thread(producer, "Producer");

        Consumer[] consumers = new Consumer[3];
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer(buffer);
            threads[i] = new Thread(consumers[i], "Consumer " + i);
        }

        producerThread.start();

        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }


    }
}
