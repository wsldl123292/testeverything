package tzb.socket.client.sender;

import tzb.socket.SocketWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static tzb.socket.Commons.DEFAULT_MESSAGE_CHARSET;
import static tzb.socket.Commons.SEND_MESSAGE;
import static tzb.socket.Commons.println;

/**
 * Created by LDL on 2017/5/25.
 */
public class MessageSender implements Sendable {

    private String message;
    private byte[] messageBytes;
    private int length = 0;

    public MessageSender(String[] tokens) throws UnsupportedEncodingException {
        if (tokens.length > 1) {
            message = tokens[1];
            this.messageBytes = message.getBytes(DEFAULT_MESSAGE_CHARSET);
            this.length = messageBytes.length;
        } else {
            throw new RuntimeException("请在sendMsg后面添加内容。");
        }
    }

    @Override
    public byte getSendType() {
        return SEND_MESSAGE;
    }

    @Override
    public void sendContent(SocketWrapper socketWrapper) throws IOException {
        println("我此时想服务器端发送消息：" + message);
        socketWrapper.write(length);
        socketWrapper.write(messageBytes);
        println("发送消息完毕。");
    }
}
