package threadsyncutilities.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 19:39
 */
public class Participant implements Runnable {

    private Videoconference videoconference;
    private String name;

    public Participant(Videoconference videoconference, String name) {
        this.videoconference = videoconference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoconference.arrive(name);
    }
}
