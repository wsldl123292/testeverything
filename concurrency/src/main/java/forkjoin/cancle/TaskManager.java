package forkjoin.cancle;

import threadsyncutilities.cyclicbarrier.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-05 22:32
 */
public class TaskManager {

    private List<ForkJoinTask<Integer>> tasks;

    public TaskManager(){
        tasks = new ArrayList<>();
    }

    public void addTask(ForkJoinTask<Integer> task){
        tasks.add(task);
    }

    public void cancleTasks(ForkJoinTask<Integer> cancelTask){
        for(ForkJoinTask<Integer> task : tasks){
            if(task!=cancelTask){
                task.cancel(true);
                ((SearchNumberTask)task).writeCancelMessage();
            }
        }
    }
}
