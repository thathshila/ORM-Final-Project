package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Student;
import lk.ijse.entity.Student_Course;

import java.io.IOException;

public interface StudentDao extends CrudDao<Student> {
    String getCurrentId() throws IOException;

    Student getStudentById(String text);

    void saveStudentCourseDetails(Student_Course studentCourse) throws IOException;

    boolean isStudentRegisteredForCourse(String stuId, String courseId) throws IOException;

    int getStudentCount();
}

