package synchronization.condition;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 14:08
 */
public class Producer implements Runnable {
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()){
            String line = mock.getLines();
            buffer.insert(line);
        }

        buffer.setPendingLines(false);
    }
}
