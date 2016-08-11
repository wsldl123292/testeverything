package customizing.threadfactory;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-11 17:27
 */
public class Main {

    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        MyTask myTask = new MyTask();
        Thread thread = myThreadFactory.newThread(myTask);

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Main: Thread information.");
        System.out.printf("%s\n",thread);
        System.out.println("Main: End of the example.");
    }
}
