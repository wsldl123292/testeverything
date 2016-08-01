package synchronization.lock;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 17:56
 */
public class Writer implements Runnable {

    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //System.out.printf("Writer: Attempt to modify the prices.\n");
        pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
        System.out.printf("Writer: Prices have been modified.\n");
    }
}
