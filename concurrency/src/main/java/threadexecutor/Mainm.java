package threadexecutor;

import java.util.concurrent.*;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-04 23:14
 */
public class Mainm {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletionService<String> service = new ExecutorCompletionService<String>(executorService);

        ReportRequest faceRequest = new ReportRequest("Face",service);

        ReportRequest onlineRequest = new ReportRequest("Online",service);

        Thread faceThread = new Thread(faceRequest);

        Thread onlineThread = new Thread(onlineRequest);

        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        System.out.printf("Main: Starting the Threads\n");
        faceThread.start();
        onlineThread.start();
        senderThread.start();

        try{
            System.out.printf("Main: Waiting for the report generators\n");
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: Shutting down the executor.\n");
        executorService.shutdown();
        try{
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processor.setEnd(true);
        System.out.printf("Main: Ends\n");
    }
}
