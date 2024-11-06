package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Dao.Custom.StudentDao;
import lk.ijse.Entity.Student;
import lk.ijse.Entity.Student_Course;
import lk.ijse.Config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean save(Student object) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student object) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Student> nativeQuery = session.createNativeQuery("delete from student where stu_id = :id");
        nativeQuery.setParameter("id", id);
        nativeQuery.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student findById(String id) {
        return null;
    }

    @Override
    public List<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM student");
        query.addEntity(Student.class);
        List<Student> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getCurrentId() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";
        Object student = session.createQuery("SELECT s.stu_id FROM Student s ORDER BY s.stu_id DESC LIMIT 1").uniqueResult();
        if (student != null) {
            String courseId = student.toString();
            String prefix = "S";
            String[] split = courseId.split(prefix);
            int idNum = Integer.parseInt(split[1]);
            nextId = prefix + String.format("%03d", ++idNum);

        } else {
            return "S001";
        }

        transaction.commit();
        session.close();
        return nextId;
    }

    @Override
    public Student getStudentById(String text) {
        Session session = null;
        Transaction transaction = null;
        Student student = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Student> query = session.createNativeQuery("SELECT * FROM student WHERE stu_id = :id", Student.class);
            query.setParameter("id", text);

            student = query.uniqueResult(); // Execute query and set the result to customer

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

        return student;
    }

    @Override
    public void saveStudentCourseDetails(Student_Course studentCourse) throws IOException {
         Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(studentCourse);
            transaction.commit();
            session.close();
        }
    }


