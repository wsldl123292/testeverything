package threadsyncutilities.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 18:03
 */
public class Consumer implements Runnable {

    private final Exchanger<List<String>> exchanger;

    private List<String> buffer;

    public Consumer(Exchanger<List<String>> exchanger, List<String> buffer) {
        this.exchanger = exchanger;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("Consumer: Cycle %d\n", cycle);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("Consumer: %d\n", buffer.size());

            for (int j = 0; j < 10; j++) {
                String message = buffer.get(0);
                System.out.println("Consumer: " + message);
                buffer.remove(0);
            }

            cycle++;
        }
    }
}
