package synchronization.condition;

import java.util.Random;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 14:09
 */
public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
