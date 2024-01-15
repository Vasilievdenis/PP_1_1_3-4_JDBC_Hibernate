package jm.task.core.jdbc.util;

import com.sun.istack.logging.Logger;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;


public class Util {

    private static final String USERNAME = "room";
    private static final String PASSWORD = "room";
    private static final String URL = "jdbc:mysql://localhost:3306/den";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/den?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static final String LOGIN = "room";
    private static final String PASWORD = "room";
    private static SessionFactory sessionFactory = null;
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

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", HOST)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size", "5")
                    .setProperty("hibernate.c3p0.max_size", "200")
                    .setProperty("hibernate.c3p0.min_statements", "200")
                    .setProperty("Environment.HBM2DDL_AUTO", "");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            logger.log(Level.INFO, "Ops!");
        }
        return sessionFactory;
    }
} // реализуйте настройку соеденения с БД

