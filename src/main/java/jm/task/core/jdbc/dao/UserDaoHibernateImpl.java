package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String TABLE_NAME = "users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL)";

        querySession(sqlCommand);
    }

    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS " + TABLE_NAME;

        querySession(sqlCommand);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT INTO " + TABLE_NAME + " (name, lastName, age) VALUES ('"
                + name + "', '" + lastName + "', '" + age + "')";

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;

        querySession(sqlCommand);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM " + TABLE_NAME;

        try (Session session = Util.getSession()) {
            session.beginTransaction();
            userList = session.createSQLQuery(sqlCommand).addEntity(User.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM " + TABLE_NAME;

        querySession(sqlCommand);
    }

    public void querySession(String sqlCommand) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
