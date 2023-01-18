package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;

    public Util() {
        String url = "jdbc:mysql://localhost/User?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Соединение прервано...");
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
