package threadsyncutilities.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 22:19
 */
public class CycMain {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS=1000;
        final int SEARCH=6;
        final int PARTICIPANTS=5;
        final int LINES_PARTICIPANT=2000;

        MatrixMock matrixMock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        Result result = new Result(ROWS);

        Grouper grouper = new Grouper(result);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS,grouper);

        Searcher[] searchers = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searchers[i] = new Searcher(i*LINES_PARTICIPANT,(i*LINES_PARTICIPANT)+LINES_PARTICIPANT,matrixMock,
                    result,SEARCH,cyclicBarrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }

        System.out.printf("Main: The main thread has finished.\n");
    }

}
