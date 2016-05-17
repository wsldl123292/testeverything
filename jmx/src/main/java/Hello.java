import javax.lang.model.element.Name;

/**
 * 功能:
 * 创建人: LDL
 * 时间:  2015/11/11 11:37
 */
public class Hello implements HelloMBean {

    private static String name;

    @Override
    public void printHello() {
        System.out.println(name);
    }

    @Override
    public void printHello(String name) {
        System.out.println("Hello," + name);
    }


    public static void main(String[] args) {
        Hello hello = new Hello();
        int i = 0;
        while (true) {

            name = i + "name";
        }
    }
}
