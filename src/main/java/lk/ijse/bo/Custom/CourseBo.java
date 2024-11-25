package lk.ijse.bo.Custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.CourseDto;
import lk.ijse.entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseBo extends SuperBo {
    public boolean save(CourseDto courseDto) throws IOException;
    public boolean update(CourseDto courseDto) throws IOException;
    public boolean delete(String id) throws IOException;
    public CourseDto getCourse(String id) throws IOException;
    public List<Course> getCourseList() throws IOException;
}
