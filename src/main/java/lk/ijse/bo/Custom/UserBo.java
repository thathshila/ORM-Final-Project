package lk.ijse.bo.Custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.util.List;
import lk.ijse.entity.User;

public interface UserBo extends SuperBo {
    public boolean save(UserDto userDto) throws IOException;
    public boolean update(UserDto userDto) throws IOException;
    public boolean delete(String id) throws IOException;
    public UserDto getUser(String id);
    public List<User> getUserList() throws IOException;

}
