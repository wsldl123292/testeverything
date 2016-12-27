package reflect;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/4/30
 */
public class User {
    private String userName;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    private String showInfo(String name,int age){
        return name+age;
    }
}
