package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection connection = null;
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/user";
    private static final String USER_NAME = "rodnoy";
    private static final String PASSWORD = "rodnoy";


    public  static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(MYSQL_URL,USER_NAME,PASSWORD);
    }
}

