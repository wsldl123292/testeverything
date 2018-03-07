package disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author liudelin
 * @date 2018/1/5 19:36
 */
public class Consumer implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private BlockingQueue<Message> blockingQueue;

    private Long startTime;

    public Consumer(BlockingQueue<Message> blockingQueue, Long startTime) {
        this.blockingQueue = blockingQueue;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        while (true) {
            Message event = null;
            try {
                event = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer value = event.getMsg();
            if (value % 10000 == 0) {
                System.out.println("By Handler : ValueEvent: " + value);
                double timeINnanos = (System.nanoTime() - startTime);
                double timetaken = (timeINnanos / 1e9);
                System.out.println("Time Taken till now in sec " + timetaken);
            }
        }

    }
}
