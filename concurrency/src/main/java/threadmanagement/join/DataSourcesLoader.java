package threadmanagement.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 17:51
 */
public class DataSourcesLoader implements Runnable{

    @Override
    public void run() {
        System.out.printf("开始加载资源: %s\n",new Date());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("资源加载结束 : %s\n",new Date());
    }
}
