package algs4.sort;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-17 15:11
 */
public class Stopwatch {

    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
