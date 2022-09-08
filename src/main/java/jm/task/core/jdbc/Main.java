package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Pasha", "Ded", (byte) 100);
        usi.saveUser("Paa", "Dd", (byte) 22);
        usi.saveUser("Inna", "Crot", (byte) 77);
        usi.saveUser("Ron", "Cat", (byte) 22);
        usi.getAllUsers().stream().forEach(System.out::println);
        usi.cleanUsersTable();
        usi.dropUsersTable();

    }
}
