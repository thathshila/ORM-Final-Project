package lk.ijse.dao.Custom.Impl;

import lk.ijse.dao.Custom.StudentCourseDao;
import lk.ijse.entity.Student_Course;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.io.IOException;
import java.util.List;

public class StudentCourseDaoImpl implements StudentCourseDao {
    @Override
    public boolean save(Student_Course object) throws IOException {
        return false;
    }

    @Override
    public boolean update(Student_Course object) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        return false;
    }

    @Override
    public Student_Course findById(String id) throws IOException {
        return null;
    }

    @Override
    public List<Student_Course> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("SELECT * FROM student_course");
        query.addEntity(Student_Course.class);
        List<Student_Course> resultList = query.getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public Student_Course getStudentCourseById(Long value) {
        Session session = null;
        Transaction transaction = null;
        Student_Course student_course = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Student_Course> query = session.createNativeQuery
                    ("SELECT * FROM student_course WHERE student_course_id = :id", Student_Course.class);
            query.setParameter("id", value);

            student_course = query.uniqueResult();

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

        return student_course;
    }
}
