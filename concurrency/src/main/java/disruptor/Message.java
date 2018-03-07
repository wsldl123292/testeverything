package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author liudelin
 * @date 2018/1/16 16:20
 */
public class Message {
    private Integer msg;

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public Integer getMsg() {
        return msg;
    }

    public final static EventFactory<Message> EVENT_FACTORY = new EventFactory<Message>() {
        @Override
        public Message newInstance() {
            return new Message();
        }
    };
}
