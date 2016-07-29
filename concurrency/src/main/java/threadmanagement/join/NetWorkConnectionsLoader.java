package threadmanagement.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 17:54
 */
public class NetWorkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("开始加载连接: %s\n",new Date());

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("连接加载结束 : %s\n",new Date());
    }
}
