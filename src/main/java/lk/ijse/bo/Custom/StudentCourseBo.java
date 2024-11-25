package lk.ijse.bo.Custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.entity.Student_Course;

import java.io.IOException;
import java.util.List;

public interface StudentCourseBo extends SuperBo {
    public List<Student_Course> getStudentCourseList() throws IOException;


}
