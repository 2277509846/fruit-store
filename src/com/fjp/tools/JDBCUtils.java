package com.fjp.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接工具类
 */
public class JDBCUtils {
    private static Connection conn = null;
    public static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) return conn;
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(new FileInputStream("database.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
