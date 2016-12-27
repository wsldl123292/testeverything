package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/5/4
 */
public class ProxyDemo {

    public static void main(String[] args) {

        /*Object[] elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i+1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null,new Class[]{Comparable.class},handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length)+1;

        int result = Arrays.binarySearch(elements,key);

        if(result>=0){
            System.out.println(elements[result]);
        }*/

        Subject realSubject = new RealSubject();
        InvocationHandler handler = new ProxySubject(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);

        subject.hello("tom");
    }
}

class TraceHandler implements InvocationHandler{

    private Object target;

    public TraceHandler(Object t){
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.print(target);
        System.out.print("." + method.getName() + "(");

        if(args!=null){
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if(i<args.length-1){
                    System.out.print(",");
                }
            }
        }

        System.out.println(")");
        return method.invoke(target,args);
    }
}
