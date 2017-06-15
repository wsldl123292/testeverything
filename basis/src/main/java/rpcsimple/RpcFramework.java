package rpcsimple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @描述
 * @作者 liudelin
 * @日期 2016/12/13 15:22
 */
public class RpcFramework {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void export(final Object service, int port) throws IOException {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }

        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }

        System.out.println("Export service " + service.getClass().getName() + " on port " + port);

        final ServerSocket serverSocket = new ServerSocket(port);

        for (; ; ) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                            try {
                                String methodName = inputStream.readUTF();
                                Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
                                Object[] arguments = (Object[]) inputStream.readObject();
                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                                    Object result = method.invoke(service, arguments);
                                    output.writeObject(result);
                                } catch (Throwable t) {
                                    output.writeObject(t);
                                } finally {
                                    output.close();
                                }
                            } finally {
                                inputStream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @SuppressWarnings("unchecked")
    static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host == null!");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        outputStream.writeUTF(method.getName());
                        outputStream.writeObject(method.getParameterTypes());
                        outputStream.writeObject(args);
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = inputStream.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            inputStream.close();
                        }
                    } finally {
                        outputStream.close();
                    }
                } finally {
                    socket.close();
                }

            }
        });
    }
}
