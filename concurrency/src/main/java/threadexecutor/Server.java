package threadexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 20:07
 */
public class Server {

    private ThreadPoolExecutor executor;

    public Server() {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrvied.\n");
        executor.execute(task);
        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
        System.out.printf("Server: Task Count: %d\n",executor.getTaskCount());
    }

    public void endServer(){
        executor.shutdown();
    }
}
