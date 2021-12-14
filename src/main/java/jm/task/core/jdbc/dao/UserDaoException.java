package jm.task.core.jdbc.dao;

public class UserDaoException extends Exception {
    public UserDaoException() {
    }

    public UserDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDaoException(String message) {
        super(message);
    }
}
