package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    public UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);
        System.out.println("Пользователь с именем - " + name +" добавлен в базу данных");
    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
        System.out.println("Пользователь удален");
    }

    public List<User> getAllUsers() {
        System.out.println(userDaoJDBC.getAllUsers().toString());
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
        System.out.println("Таблица очищена");

    }
}
