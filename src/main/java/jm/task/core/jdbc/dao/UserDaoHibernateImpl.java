package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    public void createUsersTable() {
        Transaction tс = null;
        try (Session session = Util.getSessionFactory()) {
            User user = new User();
            tс = session.beginTransaction();
            session.createSQLQuery(String.format("CREATE TABLE IF NOT EXISTS  %s ( id INT not NULL AUTO_INCREMENT, " +
                            "name VARCHAR(40), lastName VARCHAR(40), age INT, PRIMARY KEY ( id ))",
                    user.getClass().getSimpleName())).executeUpdate();

            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Transaction tс = null;
        try (Session session = Util.getSessionFactory()) {
            tс = session.beginTransaction();
            session.createSQLQuery("drop table if exists user").executeUpdate();
            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Transaction tс = null;
        try (Session session = Util.getSessionFactory()) {
            tс = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Transaction tс = null;
        try (Session session = Util.getSessionFactory()) {
            tс = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Transaction tс = null;
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory()) {
            tс = session.beginTransaction();
            User user = new User();
            users.addAll(session.createQuery("FROM " + user.getClass().getSimpleName()).getResultList());
            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Transaction tс = null;
        try (Session session = Util.getSessionFactory()) {
            tс = session.beginTransaction();
            session.createSQLQuery("DELETE FROM user" ).executeUpdate();
            tс.commit();
        } catch (Exception e) {
            tс.rollback();
            e.printStackTrace();
        }
    }

}
