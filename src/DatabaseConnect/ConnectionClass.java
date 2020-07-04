package DatabaseConnect;

import java.sql.*;

public class ConnectionClass {
    //获取到MySQL数据库的连接
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://180.76.98.154:3306/JavaBigHomework?useSSL=false&serverTimezone=UTC";


        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "142843827ccq";

        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
//        System.out.println("连接数据库...");
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

        return conn;
    }

}
