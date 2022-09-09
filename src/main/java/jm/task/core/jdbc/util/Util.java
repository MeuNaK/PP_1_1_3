package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static final String BD_URL = "jdbc:mysql://localhost/myschema";
    private static final String BD_USER = "root";
    private static final String BD_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(BD_URL, BD_USER, BD_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Configuration getConfiguration() {
         return new Configuration().addAnnotatedClass(User.class);
    }


}
