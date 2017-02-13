package threadsyncutilities.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 19:34
 */
public class VideoConference implements Runnable {

    private final CountDownLatch controller;

    VideoConference(int number) {
        controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come.\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
    }
}
