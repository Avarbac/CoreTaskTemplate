package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() throws UserDaoException {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50), lastName VARCHAR(50), " +
                    "age TINYINT)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.close();
        } catch (Exception e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void dropUsersTable() throws UserDaoException {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws UserDaoException {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void removeUserById(long id) throws UserDaoException {
        User user;
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public List<User> getAllUsers() throws UserDaoException {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }

    @Override
    public void cleanUsersTable() throws UserDaoException {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "TRUNCATE TABLE users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new UserDaoException("Произошла ошибка!");
        }
    }
}
