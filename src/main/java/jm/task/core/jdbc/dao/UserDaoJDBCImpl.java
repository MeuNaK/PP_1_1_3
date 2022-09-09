package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String NAME_DB = "Users";

    public UserDaoJDBCImpl() {
    }

    private void commandToBD(String sqlCommand) {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Connection failed :c");
            throw new RuntimeException(e);
        }
    }


    public void createUsersTable() {
        //System.out.println("Database is creating...");
        commandToBD(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                        "Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "Name VARCHAR(30), " +
                        "LastName VARCHAR(30), " +
                        "Age TINYINT UNSIGNED)",
                NAME_DB));
    }

    public void dropUsersTable() {
        //System.out.println("Database is dropping...");
        commandToBD("DROP TABLE IF EXISTS " + NAME_DB);
    }

    public void saveUser(String name, String lastName, byte age) {
        //System.out.println("User is saving...");
        commandToBD(String.format("INSERT %s (Name, LastName, Age) VALUES ('%s', '%s', '%d')",
                NAME_DB, name, lastName, age));
    }

    public void removeUserById(long id) {
        //System.out.println("User by id is removing...");
        commandToBD(String.format("DELETE FROM %s WHERE ID=%d",
                NAME_DB, id));
    }

    public void cleanUsersTable() {
        //System.out.println("Table is cleaning...");
        commandToBD(String.format("DELETE FROM %s", NAME_DB));
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sqlCommand = String.format("SELECT * FROM %s", NAME_DB);

        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                usersList.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        (byte) resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("Connection failed :c");
            throw new RuntimeException(e);
        }
        return usersList;
    }


}
