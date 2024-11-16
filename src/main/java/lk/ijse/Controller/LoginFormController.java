package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.DaoFactory;

import java.io.IOException;

public class LoginFormController {


    @FXML
    private AnchorPane anpDashboard;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        userDao.checkCredential(username, password);
    }

    public void btnForgotPasswordOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane changePasswordPane = FXMLLoader.load(this.getClass().getResource("/view/PasswordChangeForm.fxml"));

        anpDashboard.getChildren().clear();
        anpDashboard.getChildren().add(changePasswordPane);
    }
}
