package threadexecutor;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 23:04
 */
public class ReportProcessor implements Runnable {

    private CompletionService<String> service;

    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try{
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if(result!=null){
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n",report);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("ReportSender: End\n");
    }


    public void setEnd(boolean end) {
        this.end = end;
    }
}
