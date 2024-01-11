package jm.task.core.jdbc.service;

import com.sun.istack.logging.Logger;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.logging.Level;

public class UserServiceImpl implements UserService {
    public static final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName().getClass());

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        logger.log(Level.INFO, "Таблица создана");
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        logger.log(Level.INFO, "Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        logger.log(Level.INFO, "Пользователь с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        logger.log(Level.INFO, "Пользователь удален");
    }

    public List<User> getAllUsers() {
        logger.log(Level.INFO, userDaoJDBC.getAllUsers().toString());
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        logger.log(Level.INFO, "Таблица очищена");

    }
}
