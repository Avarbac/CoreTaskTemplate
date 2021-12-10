package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoException;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void createUsersTable() throws SQLException, UserDaoException;

    void dropUsersTable() throws SQLException, UserDaoException;

    void saveUser(String name, String lastName, byte age) throws UserDaoException;

    void removeUserById(long id) throws UserDaoException;

    List<User> getAllUsers() throws UserDaoException;

    void cleanUsersTable() throws UserDaoException;
}
