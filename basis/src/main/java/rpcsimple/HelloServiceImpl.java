package rpcsimple;

/**
 * @描述
 * @作者 liudelin
 * @日期 2016/12/14 15:52
 */
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "Hello " + name;
    }
}
