package threadexecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 15:08
 */
public class FactorialCalculator implements Callable<Integer> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {

        int result = -1;

        if(number==0 || number == 1){
            result = 1;
        }else {
            for (int i = 2; i <= number; i++) {
                result*=i;
                TimeUnit.SECONDS.sleep(5);
            }
        }

        System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
        return result;
    }
}
