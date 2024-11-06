package lk.ijse.Dao.Custom.Impl;

import lk.ijse.Dao.Custom.StudentCourseDao;
import lk.ijse.Entity.Student_Course;
import lk.ijse.Config.FactoryConfiguration;
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
}
