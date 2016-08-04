package threadexecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 21:34
 */
public class ResultTask extends FutureTask<String> {

    private String name;

    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask)callable).getName();
    }

    @Override
    protected void done() {
        if(isCancelled()){
            System.out.printf("%s: Has been canceled\n",name);
        }else{
            System.out.printf("%s: Has finished\n",name);
        }
        super.done();
    }
}
