package lk.ijse.Dao.Custom;

import lk.ijse.Dao.CrudDao;
import lk.ijse.Entity.User;

import java.io.IOException;

public interface UserDao extends CrudDao<User> {
    String getCurrentId() throws IOException;
}
