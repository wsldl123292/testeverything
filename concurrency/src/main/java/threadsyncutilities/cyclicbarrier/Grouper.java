package threadsyncutilities.cyclicbarrier;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 22:15
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = result.getData();
        for (int number : data) {
            finalResult += number;
        }

        System.out.printf("Grouper: Total result: %d.\n",finalResult);
    }
}
