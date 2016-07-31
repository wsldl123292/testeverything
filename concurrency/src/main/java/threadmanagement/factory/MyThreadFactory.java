package threadmanagement.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-31 16:54
 */
public class MyThreadFactory implements ThreadFactory {

    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name){
        counter = 0;
        this.name = name;
        stats = new ArrayList<>();

    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r,name+"-Thread_"+counter);
        counter++;
        stats.add(String.format("Create thread %d with name %s on %s\n", thread.getId(), thread.getName(),
                new Date()));

        return thread;
    }


    public String getStatistics(){
        StringBuilder buffer = new StringBuilder();
        for (String stat : stats) {
            buffer.append(stat).append("\n");
        }

        return buffer.toString();
    }
}
