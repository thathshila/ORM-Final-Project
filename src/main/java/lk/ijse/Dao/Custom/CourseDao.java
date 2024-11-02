package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.Course;

import java.io.IOException;

public interface CourseDao extends CrudDao<Course>{
    String getCurrentId() throws IOException;
}
