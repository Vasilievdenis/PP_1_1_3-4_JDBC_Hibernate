package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnectiion();
    }

    public void createUsersTable() {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                        " id INT NOT NULL AUTO_INCREMENT," +
                        " name VARCHAR(40)," +
                        "lastName VARCHAR(40)," +
                        "age INT," +
                        "PRIMARY KEY(id));");
            }
        } catch (SQLException e) {
            System.out.println("При создании таблицы возникла ошибка");
        }
    }

    public void dropUsersTable() {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE IF EXISTS users");
            }
        } catch (SQLException e) {
            throw new RuntimeException("При удалении пользователя возникла ошибка", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setString(3, String.valueOf(age));
                statement.executeUpdate();
                System.out.println("Пользователь" + name + "успешно добавлен");
            }
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении пользователя возникла ошибка", e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении пользователя", e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Возникла ошибка при извлечении пользователя", e);
        }
        return  userList;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw  new RuntimeException("При очистки таблицы возникла ошибка", e);
        }
    }
}
