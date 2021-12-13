package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoException;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();

    public void createUsersTable() throws UserDaoException {
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws UserDaoException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws UserDaoException {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws UserDaoException {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws UserDaoException {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws UserDaoException {
        userDao.cleanUsersTable();
    }
}
