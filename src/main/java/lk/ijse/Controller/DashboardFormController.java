package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class  DashboardFormController {

    @FXML
    private AnchorPane anpMain;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnUser;

    @FXML
    private Label lblCurrentUser;

    @FXML
    private Label lblDate;

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        AnchorPane coursePane = FXMLLoader.load(this.getClass().getResource("/view/CourseForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(coursePane);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane userPane = FXMLLoader.load(this.getClass().getResource("/view/UserForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(userPane);
    }

}
