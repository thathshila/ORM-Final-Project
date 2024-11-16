package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainFormController {

    @FXML
    private AnchorPane anpMain;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnUser;

    @FXML
    private Label lblCurrentUser;

    @FXML
    private Label lblDate;

    public void initialize() {
        // Format the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);

        // Set the date and time to the label
        lblDate.setText(formattedDateTime);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane paymentPane = FXMLLoader.load(this.getClass().getResource("/view/PaymentForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(paymentPane);
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        AnchorPane coursePane = FXMLLoader.load(this.getClass().getResource("/view/CourseForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(coursePane);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane studentPane = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(studentPane);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane userPane = FXMLLoader.load(this.getClass().getResource("/view/UserForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(userPane);
    }

    @FXML
    void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane settingPane = FXMLLoader.load(this.getClass().getResource("/view/SettingForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(settingPane);
    }
}
