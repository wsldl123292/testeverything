package struct.blocking;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-08 15:43
 */
public class Client implements Runnable {

    private LinkedBlockingDeque<String> requestList;

    public Client(LinkedBlockingDeque<String> requestList) {
        this.requestList = requestList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuffer request = new StringBuffer();
                request.append(i);
                request.append(":");
                request.append(j);

                try {
                    requestList.add(request.toString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

                System.out.printf("Client: %s at %s.\n",request,new Date());
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Client: End.\n");
    }
}
