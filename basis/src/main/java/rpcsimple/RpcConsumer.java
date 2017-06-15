package rpcsimple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述
 * @作者 liudelin
 * @日期 2016/12/14 15:56
 */
public class RpcConsumer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        final HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        /*for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String hello = service.hello("World" + Thread.currentThread().getName());
                    System.out.println(hello);
                }
            });
        }

        executorService.shutdown();*/
        String hello = service.hello("World" + Thread.currentThread().getName());
        System.out.println(hello);
    }
}
