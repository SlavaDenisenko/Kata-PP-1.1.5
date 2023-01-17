package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable() throws ClassNotFoundException;

    void dropUsersTable() throws ClassNotFoundException;

    void saveUser(String name, String lastName, byte age) throws ClassNotFoundException;

    void removeUserById(long id) throws ClassNotFoundException;

    List<User> getAllUsers() throws ClassNotFoundException;

    void cleanUsersTable() throws ClassNotFoundException;
}
