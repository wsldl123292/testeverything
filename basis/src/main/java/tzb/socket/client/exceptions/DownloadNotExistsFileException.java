package tzb.socket.client.exceptions;

/**
 * Created by LDL on 2017/5/24.
 */
public class DownloadNotExistsFileException extends RuntimeException{

    private static final long serialVersionUID = 2969567696674112542L;

    public DownloadNotExistsFileException(String path) {
        super("无法下载文件：" + path + "，因为服务器端不存在这个文件....");
    }

}
