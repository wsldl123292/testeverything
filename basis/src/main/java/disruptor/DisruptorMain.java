package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/6/8 14:29
 */
public class DisruptorMain {

    public static void main(String[] args) throws InterruptedException {

        class Element {
            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        //生产者
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        //RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> eventFactory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        // 处理Event的handler
        EventHandler<Element> handler = new EventHandler<Element>() {
            @Override
            public void onEvent(Element element, long l, boolean b) throws Exception {
                System.out.println("Element: " + element.getValue());
            }
        };

        int buffSize = 16;
        Disruptor<Element> disruptor = new Disruptor<>(eventFactory, buffSize, threadFactory, ProducerType.MULTI, new BlockingWaitStrategy());

        //noinspection unchecked
        disruptor.handleEventsWith(handler);
        disruptor.start();


        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        int l = 0;
        while (true) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.setValue(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
            l++;
        }
    }

}
