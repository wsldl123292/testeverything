package tzb.socket.client.sender;

import tzb.socket.SocketWrapper;

import java.io.IOException;

/**
 * Created by LDL on 2017/5/25.
 */
public interface Sendable {
    public byte getSendType();

    public void sendContent(SocketWrapper socketWrapper) throws IOException;
}
