package synchronization.lock;


/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 17:34
 */
public class LockMain {
    public static void main(String[] args) {
//        PrintQueue printQueue = new PrintQueue();
//        Thread[] threads = new Thread[10];
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new Thread(new Job(printQueue),"Thread "+i);
//        }
//
//        for (Thread thread : threads) {
//            thread.start();
//        }


        PricesInfo pricesInfo = new PricesInfo();
        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i = 0; i < 5; i++){
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);
        threadWriter.start();

        for (int i = 0; i < 2; i++){
            threadsReader[i].start();
        }

    }
}
