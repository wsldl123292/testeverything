package threadmanagement.factory;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-31 17:03
 */
public class FactoryMain {

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();

        Thread thread;
        System.out.printf("Starting the Threads\n");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }

        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n", factory.getStatistics());
    }
}
