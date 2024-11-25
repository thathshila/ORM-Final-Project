package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dao.Custom.CourseDao;
import lk.ijse.dao.Custom.StudentDao;
import lk.ijse.dao.Custom.UserDao;
import lk.ijse.dao.DaoFactory;

public class DashboardFormController {

    @FXML
    private AnchorPane anpDash;

    @FXML
    private Label program;

    @FXML
    private Label lblStudent;

    @FXML
    private Label lblUser;

    private int programCount;

    private int studentCount;

    private int userCount;

    CourseDao courseDao = (CourseDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.COURSE);
    StudentDao studentDao = (StudentDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.STUDENT);
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);

    public void initialize() {
       programCountCal();
       studentCountCal();
       userCountCal();
    }

    private void userCountCal() {
        userCount = userDao.getUserCount();
        setUserCount(userCount);
    }

    private void setUserCount(int userCount) {
        lblUser.setText(String.valueOf(userCount));
    }

    private void studentCountCal() {
        studentCount = studentDao.getStudentCount();
        setStudentCount(studentCount);
    }

    private void setStudentCount(int studentCount) {
        lblStudent.setText(String.valueOf(studentCount));
    }

    public void programCountCal(){
        programCount = courseDao.getProgramCount();
        setProgramCount(programCount);
    }

    public void setProgramCount(int programCount) {
        program.setText(String.valueOf(programCount));
    }
}
