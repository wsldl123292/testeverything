package multidb;

import java.util.Map;

/**
 * 作者: LDL
 * 功能说明: 自定义函数
 * 创建日期: 2015/9/1 11:17
 */
public class UDF {

    /**
     * 函数id
     */
    private int id;
    /**
     * 函数名称
     */
    private String fName;
    /**
     * 函数参数
     */
    private Map<String,String> fParams;
    /**
     * 函数内容
     */
    private String fContent;

    /**
     * 函数返回值
     */
    private Map<String,String> fReturn;
    private int fType;

    public int getfType() {
        return fType;
    }

    public void setfType(int fType) {
        this.fType = fType;
    }


    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getfParams() {
        return fParams;
    }

    public void setfParams(Map<String, String> fParams) {
        this.fParams = fParams;
    }

    public Map<String, String> getfReturn() {
        return fReturn;
    }

    public void setfReturn(Map<String, String> fReturn) {
        this.fReturn = fReturn;
    }
}
