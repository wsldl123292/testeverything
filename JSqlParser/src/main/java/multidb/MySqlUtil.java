package multidb;


import java.sql.*;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/31 18:27
 */
public class MySqlUtil {

    public static final String url = "jdbc:mysql://192.168.1.204/internetdb";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123!@#";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
