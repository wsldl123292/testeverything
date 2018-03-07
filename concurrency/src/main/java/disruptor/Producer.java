package disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * @author liudelin
 * @date 2018/1/16 16:21
 */
public class Producer implements Runnable {

    static public Integer maxMsg = 1000000;
    static public int multiply = 10;
    private RingBuffer<Message> rb;

    public Producer(RingBuffer rb) {
        this.rb = rb;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxMsg * multiply; i++) {
            long seq = rb.next();
            Message msg = rb.get(seq);
            msg.setMsg(i);
            rb.publish(seq);
        }

        System.out.println("done sending " + maxMsg * multiply + " messages");
    }

}