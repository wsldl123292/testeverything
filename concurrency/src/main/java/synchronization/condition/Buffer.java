package synchronization.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-02 13:49
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition space;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {

                space.await();

            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public String get() {
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && hasPendingLines()) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return line;
    }


    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }
}
