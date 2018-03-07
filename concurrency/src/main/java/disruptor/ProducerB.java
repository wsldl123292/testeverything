package disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author liudelin
 * @date 2018/1/5 19:36
 */
public class ProducerB implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    static public Integer maxMsg = 1000000;
    static public int multiply = 10;

    private BlockingQueue<Message> blockingQueue;

    public ProducerB(BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxMsg * multiply; i++) {
            Message msg = new Message();
            msg.setMsg(i);
            try {
                blockingQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("done sending " + maxMsg * multiply + " messages");
    }
}
