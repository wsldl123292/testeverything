package threadsyncutilities.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 20:24
 */
public class Searcher implements Runnable {


    private int firstRow;
    private int lastRow;

    private MatrixMock matrixMock;
    private Result result;
    private int number;
    private final CyclicBarrier cyclicBarrier;

    public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Result result, int number, CyclicBarrier cyclicBarrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.matrixMock = matrixMock;
        this.result = result;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(),
                firstRow, lastRow);

        for (int i = firstRow; i < lastRow; i++) {
            int[] row = matrixMock.getRow(i);
            counter = 0;
            for (int j = 0; j < row.length; j++) {
                if(row[j] == number){
                    counter++;
                }
            }
            result.setData(i, counter);
        }

        System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
