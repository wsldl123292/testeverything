package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/5/5
 */
public class ProxySubject implements InvocationHandler {

    private Object target;

    public ProxySubject(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("执行hello开始..."+args[0]);

        method.invoke(target, args);

        System.out.println("执行hello结束...");
        return null;
    }
}
