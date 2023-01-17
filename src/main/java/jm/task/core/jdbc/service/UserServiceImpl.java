package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    public void createUsersTable() throws ClassNotFoundException {
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws ClassNotFoundException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws ClassNotFoundException {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws ClassNotFoundException {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws ClassNotFoundException {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws ClassNotFoundException {
        userDao.cleanUsersTable();
    }
}
