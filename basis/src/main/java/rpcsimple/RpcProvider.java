package rpcsimple;

import java.io.IOException;

/**
 * @描述
 * @作者 liudelin
 * @日期 2016/12/14 15:53
 */
public class RpcProvider {
    public static void main(String[] args) throws IOException {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
