package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Entity.User;
import lk.ijse.Config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(User object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<User> nativeQuery = session.createNativeQuery("delete from user where user_id = :id");
        nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM user");
        query.addEntity(User.class);
        List<User> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object user = session.createQuery("SELECT U.user_id  FROM User U ORDER BY U.user_id DESC LIMIT 1").uniqueResult();
        if (user != null) {
            String userId = user.toString();
            String prefix = "U";
            String[] split = userId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "U001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }

    @Override
    public List<String> getUserId() {
        Session session = null;
        Transaction transaction = null;
        List<String> userIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            userIds = session.createQuery("SELECT u.user_id FROM User u", String.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userIds;
    }

    @Override
    public User getUserById(String value) {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM user U WHERE U.user_id = :id", User.class);
            query.setParameter("id",value);

            user = query.uniqueResult(); // Execute query and set the result to customer

            transaction.commit(); // Commit the transaction if successful
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction if an error occurs
            }
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (session != null) {
                session.close(); // Ensure session is closed
            }
        }

        return user;
    }
}



