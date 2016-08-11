package customizing;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-09 22:28
 */
public class MyExecutor extends ThreadPoolExecutor {

    private ConcurrentHashMap<String,Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.printf("MyExecutor: Going to shutdown.\n");
        System.out.printf("MyExecutro: Executor tasks: %d\n",getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.printf("MyExecutor: Going to immediately shutdown.\n");
        System.out.printf("MyExecutro: Executor tasks: %d\n",getCompletedTaskCount());
        System.out.printf("MyExecutor: Running tasks: %d\n",getActiveCount());
        System.out.printf("MyExecutor: Pending tasks: %d\n",getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor: A task is beginning: %s : %s\n",t.getName(),r.hashCode());
        startTimes.put(String.valueOf(r.hashCode()),new Date());
        //super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future future = (Future) r;
        try{
            System.out.println("*********************************");
            System.out.printf("MyExecutor: A task is finishing.\n");
            System.out.printf("MyExecutor: Result: %s\n",future.get());
            Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
            Date finishDate = new Date();
            long diff = finishDate.getTime() - startDate.getTime();
            System.out.printf("MyExecutor: Duration: %d\n",diff);
            System.out.println("*********************************");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //super.afterExecute(r, t);
    }
}
