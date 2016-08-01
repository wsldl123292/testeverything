package synchronization.lock;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 17:56
 */
public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread()
                    .getName(), pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread()
                    .getName(), pricesInfo.getPrice2());
        //}

    }
}
