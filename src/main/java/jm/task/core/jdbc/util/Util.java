package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mydbtest?allowPublicKeyRetrieval=true&useSSL=false";
    private static Connection connection;

    public static Connection getConnection() {

        try {
            return connection = DriverManager.getConnection(connectionURL,userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
