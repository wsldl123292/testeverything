package disruptor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liudelin
 * @date 2018/1/16 16:21
 */
public class SampleB {
    public static void main(String[] args) {
        final long startTime = System.nanoTime();
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>(1000);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(15);
        executorService.execute(new ProducerB(blockingQueue));
        for (int i = 0; i < 15; i++) {
            executorService1.execute(new Consumer(blockingQueue, startTime));
        }
    }
}
