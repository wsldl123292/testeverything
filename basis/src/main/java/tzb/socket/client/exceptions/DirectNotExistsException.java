package tzb.socket.client.exceptions;

/**
 * Created by LDL on 2017/5/25.
 */
public class DirectNotExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DirectNotExistsException(String directPath) {
        super("在本地没有找到目录：" + directPath);
    }

}
