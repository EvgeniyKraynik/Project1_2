package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private static Transaction transaction;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(15), lastname VARCHAR(30), age TINYINT);").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User;").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id=id").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE my_db.User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try{
                    transaction.rollback();
                } catch (HibernateException ex){
                    ex.printStackTrace();}
            }
            e.printStackTrace();
        }
    }
}
