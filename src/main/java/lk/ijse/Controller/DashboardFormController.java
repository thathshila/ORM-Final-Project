package lk.ijse.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dao.Custom.CourseDao;
import lk.ijse.Dao.DaoFactory;

import java.sql.SQLException;

public class DashboardFormController {

    @FXML
    private AnchorPane anpDash;

    @FXML
    private Label program;

    private int programCount;

    CourseDao courseDao = (CourseDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.COURSE);

    public void initialize() {
        programCount = courseDao.getProgramCount();
        setProgramCount(programCount);
    }

    public void setProgramCount(int programCount) {
        program.setText(String.valueOf(programCount));
    }
}
