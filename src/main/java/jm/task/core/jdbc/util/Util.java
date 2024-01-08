package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String USERNAME = "room";
    public static final String PASSWORD = "room";
    public static final String URL = "jdbc:mysql://localhost:3306/den";
    public static Connection getConnectiion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
        return connection;
    }
} // реализуйте настройку соеденения с БД

