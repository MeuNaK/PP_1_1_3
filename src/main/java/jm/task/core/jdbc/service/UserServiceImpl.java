package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


}
