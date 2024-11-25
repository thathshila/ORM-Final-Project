package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Student_Course;

public interface StudentCourseDao extends CrudDao<Student_Course> {
    Student_Course getStudentCourseById(Long value);
}
