package jm.task.core.jdbc.dao;

import com.sun.istack.logging.Logger;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    private final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName().getClass());

    public UserDaoJDBCImpl() {
        connection = Util.getConnectiion();
    }

    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (" + " id INT NOT NULL AUTO_INCREMENT," + " name VARCHAR(40)," + "lastName VARCHAR(40)," + "age INT," + "PRIMARY KEY(id));");
        } catch (SQLException e) {
            logger.log(Level.INFO, "При создании таблицы возникла ошибка");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException("При удалении пользователя возникла ошибка", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));
            preparedStatement.executeUpdate();
            System.out.println("Пользователь" + name + "успешно добавлен");
        } catch (SQLException e) {
            throw new RuntimeException("При сохранении пользователя возникла ошибка", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении пользователя", e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
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
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("При очистки таблицы возникла ошибка", e);
        }
    }
}
