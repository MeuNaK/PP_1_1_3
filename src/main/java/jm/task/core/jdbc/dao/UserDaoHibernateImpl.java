package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Configuration conf = Util.getConfiguration();
        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "Name VARCHAR(30), " +
                    "LastName VARCHAR(30), " +
                    "Age TINYINT UNSIGNED)").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        Configuration conf = Util.getConfiguration();
        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Configuration conf = Util.getConfiguration();

        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.save(new User(name, lastName, age));

            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        Configuration conf = Util.getConfiguration();

        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Configuration conf = Util.getConfiguration();
        List<User> userList;
        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            userList = session.createQuery("FROM User").getResultList();

            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Configuration conf = Util.getConfiguration();
        try (SessionFactory sessionFactory = conf.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("DELETE FROM User").executeUpdate();

            session.getTransaction().commit();
        }

    }
}
