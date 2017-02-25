package base;

import java.util.concurrent.*;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/2/25 15:31
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        final ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10, false);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.add(i+"");
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(blockingQueue.size());
                while(blockingQueue.size() > 0){
                    System.out.println(blockingQueue.poll());
                }
            }
        });


        executor.shutdown();
    }
}
