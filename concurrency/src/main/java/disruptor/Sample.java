package disruptor;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liudelin
 * @date 2018/1/16 16:21
 */
public class Sample {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // Preallocate RingBuffer with 1024 ValueEvents
        Disruptor<Message> disruptor = new Disruptor<Message>(Message.EVENT_FACTORY, 2048, exec,
                ProducerType.SINGLE, new BusySpinWaitStrategy());
        // make handler
        final long startTime = System.nanoTime();
        final EventHandler<Message> handler = new EventHandler<Message>() {
            // event will eventually be recycled by the Disruptor after it wraps
            @Override
            public void onEvent(final Message event, final long sequence, final boolean endOfBatch) throws Exception {
                Integer value = event.getMsg();
                if (value % 10000 == 0) {
                    System.out.println("By Handler : ValueEvent: " + value + " Sequence: " + sequence);
                    double timeINnanos = (System.nanoTime() - startTime);
                    double timetaken = (timeINnanos / 1e9);
                    System.out.println("Time Taken till now in sec " + timetaken);
                }
            }
        };
        /*final EventHandler<Message> handler2 = new EventHandler<Message>() {
            // event will eventually be recycled by the Disruptor after it wraps
            @Override
            public void onEvent(final Message event, final long sequence, final boolean endOfBatch) {
                Integer value = event.getMsg();
                if (value % 10000 == 0) {
                    System.out.println("By Handler2 :ValueEvent: " + value + " Sequence: " + sequence);
                    double timeINnanos = (System.nanoTime() - startTime);
                    double timetaken = (timeINnanos / 1e9);
                    System.out.println("Time Taken till now in sec " + timetaken);
                }
            }
        };*/
        // register handler with disruptor
        disruptor.handleEventsWith(handler);

        RingBuffer<Message> ringBuffer = disruptor.start();

        Producer producer = new Producer(ringBuffer);

        //starting producer to produce messages in queue
        Thread p = new Thread(producer);
        p.start();
        try {
            p.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
