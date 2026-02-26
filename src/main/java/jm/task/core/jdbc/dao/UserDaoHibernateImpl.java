package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.HibernateUtil.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "lastName VARCHAR(100) NOT NULL, "
                    + "age TINYINT"
                    + ")";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null ) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            Query query = session.createSQLQuery(sql)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "DELETE FROM users WHERE id = ?";
            Query query = session.createSQLQuery(sql)
                    .setParameter(1, id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> userList = new ArrayList<>();
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "SELECT id, name, lastName, age FROM users";
            userList  = session.createSQLQuery(sql).addEntity(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String sql = "TRUNCATE users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}