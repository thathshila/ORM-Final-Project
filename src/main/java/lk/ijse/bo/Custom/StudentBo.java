package lk.ijse.bo.Custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.StudentDto;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentBo extends SuperBo {
    public boolean save(StudentDto studentDto) throws IOException;
    public boolean update(StudentDto studentDto) throws IOException;
    public boolean delete(String id) throws IOException;
    public StudentDto getStudent(String id);
    public List<Student> getStudentList() throws IOException;
}
