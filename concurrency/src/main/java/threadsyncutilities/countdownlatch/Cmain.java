package threadsyncutilities.countdownlatch;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 19:41
 */
public class Cmain {

    public static void main(String[] args) {
        VideoConference videoConference = new VideoConference(10);
        Thread threadConference = new Thread(videoConference);
        threadConference.start();

        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(videoConference, "Participant " + i);
            Thread thread = new Thread(participant);
            thread.start();
        }
    }
}
