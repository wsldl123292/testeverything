package threadexecutor;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-03 20:11
 */
public class Emain {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task "+i);
            server.executeTask(task);
        }

        server.endServer();
    }
}
