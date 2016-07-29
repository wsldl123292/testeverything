package threadmanagement.join;


import java.util.Date;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-07-29 17:55
 */
public class Main {

    public static void main(String[] args) {
        DataSourcesLoader dataSourcesLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dataSourcesLoader);

        NetWorkConnectionsLoader netWorkConnectionsLoader = new NetWorkConnectionsLoader();
        Thread thread2 = new Thread(netWorkConnectionsLoader);


        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.printf("配置加载完成 : %s\n",new Date());
    }
}
