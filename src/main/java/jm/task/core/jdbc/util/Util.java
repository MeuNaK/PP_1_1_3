package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String BD_URL = "jdbc:mysql://localhost/myschema";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "кщще";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
