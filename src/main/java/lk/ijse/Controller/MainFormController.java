package lk.ijse.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.DaoFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainFormController {

    String username;

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

    private boolean userAllowed;

    private boolean studentAllowed;

    private boolean paymentAllowed;

    private boolean programAllowed;

    private boolean settingsAllowed;

    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);

    public void initialize() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd           HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String formattedDateTime = LocalDateTime.now().format(formatter);
            lblDate.setText(formattedDateTime);
        }));

        clock.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        clock.play();
        loadDashboardForm();
    }

    public void loadDashboardForm() throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(dashboardPane);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane dashboardPane = FXMLLoader.load(this.getClass().getResource("/view/DashboardForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(dashboardPane);
        setAccess("Admin");
        setAccess("Coordinator");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane paymentPane = FXMLLoader.load(this.getClass().getResource("/view/PaymentForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(paymentPane);
        setAccess("Admin");
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        AnchorPane coursePane = FXMLLoader.load(this.getClass().getResource("/view/CourseForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(coursePane);
        setAccess("Admin");
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane studentPane = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(studentPane);
        setAccess("Admin");
        setAccess("Coordinator");
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        if (userAllowed) {
        AnchorPane userPane = FXMLLoader.load(this.getClass().getResource("/view/UserForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(userPane);
        } else {
            // Show an alert if access is denied
            showAlert("Access Denied", "You do not have permission to access the User form.");
        }
        setAccess("Admin");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane settingPane = FXMLLoader.load(this.getClass().getResource("/view/SettingForm.fxml"));

        anpMain.getChildren().clear();
        anpMain.getChildren().add(settingPane);
        setAccess("Admin");
    }

    public void setUsername(String username) {
        this.username = username;
        String userRole = getUserRole(username);
        setUserRole(userRole);
    }

    private String getUserRole(String username) {
        return userDao.getUserRole(username);
    }

    private void setUserRole(String userRole) {
        if (userRole != null) {
            lblCurrentUser.setText(userRole);
        } else {
            lblCurrentUser.setText("Role not found");
        }
    }

    public void setAccess(String userRole) {
        lblCurrentUser.setText(userRole);

    // Reset all access to false initially
    userAllowed = false;
    studentAllowed = false;
    paymentAllowed = false;
    programAllowed = false;
    settingsAllowed = false;

    if (userRole != null) {
        System.out.println("userRole: " + userRole);

        switch (userRole) {
            case "Admin":
                userAllowed = true;
                studentAllowed = true;
                paymentAllowed = true;
                programAllowed = true;
                settingsAllowed = true;
                break;

            case "Coordinator":
                studentAllowed = true;
                break;

            default:
                // No access for other roles
                break;
        }

        // Enable or disable buttons based on access levels
        btnUser.setDisable(!userAllowed);
        btnStudent.setDisable(!studentAllowed);
        btnPayment.setDisable(!paymentAllowed);
        btnProgram.setDisable(!programAllowed);
        btnSettings.setDisable(!settingsAllowed);
    } else {
        lblCurrentUser.setText("No role assigned");
    }
    }
}

