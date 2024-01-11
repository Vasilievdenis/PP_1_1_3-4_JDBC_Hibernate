package jm.task.core.jdbc.util;

import com.sun.istack.logging.Logger;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class Util {
    public static final String USERNAME = "room";
    public static final String PASSWORD = "room";
    public static final String URL = "jdbc:mysql://localhost:3306/den";
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName().getClass());

    public static Connection getConnectiion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.INFO, "Не удалось загрузить класс драйвера");
        }
        return connection;
    }
} // реализуйте настройку соеденения с БД

