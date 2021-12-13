package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static Logger log = Logger.getLogger(User.class.getName());

    @Override
    public void createUsersTable() throws UserDaoException {
        Connection connection = Util.getConnection();

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age INT)");
            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void dropUsersTable() throws UserDaoException {
        Connection connection = Util.getConnection();

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS users");
            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws UserDaoException {
        Connection connection = Util.getConnection();

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void removeUserById(long id) throws UserDaoException {
        Connection connection = Util.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM users WHERE ID = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public List<User> getAllUsers() throws UserDaoException {
        Connection connection = Util.getConnection();
        List<User> allUsers = new ArrayList<>();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                allUsers.add(user);
            }
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() throws UserDaoException {
        Connection connection = Util.getConnection();

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "TRUNCATE TABLE users");
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UserDaoException("Произошла ошибка!");
        }

    }
}
