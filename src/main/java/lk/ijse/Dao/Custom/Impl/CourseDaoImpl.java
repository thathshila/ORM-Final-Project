package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Dao.Custom.CourseDao;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.User;
import lk.ijse.Util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    @Override
    public boolean save(Course object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course object) throws IOException {
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
        NativeQuery<User> nativeQuery = session.createNativeQuery("delete from course where course_id = :id");
        nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Course findById(String id) {
        return null;
    }

    @Override
    public List<Course> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM course");
        query.addEntity(Course.class);
        List<Course> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object course = session.createQuery("SELECT C.course_id  FROM Course C ORDER BY C.course_id DESC LIMIT 1").uniqueResult();
        if (course != null) {
            String courseId = course.toString();
            String prefix = "C";
            String[] split = courseId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "C001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }
}
