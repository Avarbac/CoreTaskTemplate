package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUsersTable() throws UserDaoException;

    void dropUsersTable() throws UserDaoException;

    void saveUser(String name, String lastName, byte age) throws UserDaoException;

    void removeUserById(long id) throws UserDaoException;

    List<User> getAllUsers() throws UserDaoException;

    void cleanUsersTable() throws UserDaoException;
}
