package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String tableName = "users";

    public UserDaoJDBCImpl() {}

    public void createUsersTable() throws ClassNotFoundException {
        Util util = new Util();
        String sqlCommand = "CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), lastName VARCHAR(45), age INT)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            statement.execute(sqlCommand);

            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() throws ClassNotFoundException {
        Util util = new Util();
        String sqlCommand = "DROP TABLE " + tableName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            statement.execute(sqlCommand);

            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws ClassNotFoundException {
        Util util = new Util();
        String attribute2 = "name";
        String attribute3 = "lastName";
        String attribute4 = "age";
        String sqlCommand = "INSERT " + tableName + "(" + attribute2 + ", " + attribute3 + ", "
                + attribute4 + ") VALUE ('" + name + "', '" + lastName + "', " + age + ")";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlCommand);

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) throws ClassNotFoundException {
        Util util = new Util();
        String attribute1 = "id";
        String sqlCommand = "DELETE FROM " + tableName + " WHERE " + attribute1 + " = " + id;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws ClassNotFoundException {
        Util util = new Util();
        List<User> list = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() throws ClassNotFoundException {
        Util util = new Util();
        String sqlCommand = "DELETE FROM " + tableName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
