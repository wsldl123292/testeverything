package disruptor;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/6/7 15:39
 */
public class FalseSharing implements Runnable {
    public final static int NUM_THREADS = 3; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
    private final int arrayIndex;

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println("duration = " + (System.currentTimeMillis() - start)/1000);
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static final class VolatileLong {
        public long p1, p2, p3, p4, p5, p6; // comment out
        public volatile long value = 0L;
        public long p7, p8, p9, p10, p11, p12; // comment out

    }
}
