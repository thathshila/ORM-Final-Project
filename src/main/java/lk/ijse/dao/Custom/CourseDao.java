package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDao;
import lk.ijse.entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseDao extends CrudDao<Course>{
    String getCurrentId() throws IOException;

    List<String> getCourseId();

    List<String> getCourseIds();

    Course getCourseById(String courseId);

    int getProgramCount();
}

