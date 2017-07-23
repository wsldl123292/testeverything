package org.smart4j.framework;

import org.smart4j.framework.core.ConfigHelper;

public class FrameworkConstant {

    public static String UTF_8 = "UTF-8";

    public static String CONFIG_PROPS = "smart.properties";
    public static String SQL_PROPS = "smart-sql.properties";

    public static String PLUGIN_PACKAGE = "org.smart4j.plugin";

    public static String JSP_PATH = ConfigHelper.getString("smart.framework.app.jsp_path", "/WEB-INF/jsp/");
    public static String WWW_PATH = ConfigHelper.getString("smart.framework.app.www_path", "/www/");
    public static String HOME_PAGE = ConfigHelper.getString("smart.framework.app.home_page", "/index.html");
    public static int UPLOAD_LIMIT = ConfigHelper.getInt("smart.framework.app.upload_limit", 10);

    public static String PK_NAME = "id";
}
