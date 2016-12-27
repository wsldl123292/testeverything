package proxy;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/5/5
 */
public class RealSubject implements Subject {
    @Override
    public void hello(String name) {
        System.out.println("hello : "+name);
    }
}
