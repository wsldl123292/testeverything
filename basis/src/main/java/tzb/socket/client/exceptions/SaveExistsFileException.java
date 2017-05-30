package tzb.socket.client.exceptions;

/**
 * Created by LDL on 2017/5/24.
 */
public class SaveExistsFileException extends RuntimeException{
    private static final long serialVersionUID = -1026575092082314002L;

    public SaveExistsFileException(String path) {
        super("保存文件:" + path + "失败，因为文件已经存在了。");
    }
}
