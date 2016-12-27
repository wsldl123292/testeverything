package reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/4/30
 */
public class Reflect {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {

        //reflectMethod();

        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a,10);
        System.out.println(Arrays.toString(a));

        String[] b = {"Tom","Dick","Harry"};
        b = (String[]) goodCopyOf(b,10);
        System.out.println(Arrays.toString(b));
        /*String[] b = {"Tom","Dick","Harry"};
        b = (String[]) badCopyOf(b,10);*/
    }


    public static void reflectValue() throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        Field[] fields = user.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if(fields[i].getType()==String.class){
                fields[i].set(user, "hello");
            }else{
                fields[i].setInt(user,10);
            }
            //System.out.println(fields[i].get(user));
        }
        System.out.println(user.getAge());
    }


    public static void reflectMethod() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        Class aClass = Class.forName("reflect.User");
        User user = (User) aClass.newInstance();
        Method[] methods = user.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            method.setAccessible(true);
            if(method.getName().equals("showInfo")){
                System.out.println(method.invoke(user, new Object[]{"hello", 1}));
            }
        }

    }


    public static Object[] badCopyOf(Object[] a ,int newLength){
        Object[] newArray = new Object[newLength];
        System.arraycopy(a,0,newArray,0, Math.min(a.length,newLength));
        return newArray;
    }


    public static Object goodCopyOf(Object a,int newLength){

        Class cl = a.getClass();
        if(!cl.isArray()){
            return null;
        }

        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType,length);
        System.arraycopy(a,0,newArray,0, Math.min(length,newLength));
        return newArray;
    }
}
