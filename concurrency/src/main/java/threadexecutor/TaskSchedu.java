package threadexecutor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 18:05
 */
public class TaskSchedu implements Runnable {

    private String name;

    public TaskSchedu(String name) {
        this.name = name;
    }

    @Override
    public void run() {

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.printf("%s: Starting at : %s\n",name,new Date());
    }
}
