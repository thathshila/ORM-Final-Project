package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.Custom.UserBo;
import lk.ijse.dao.Custom.UserDao;
import lk.ijse.dao.DaoFactory;
import lk.ijse.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordChangeFormController{

    @FXML
    private AnchorPane anpChangePassword;

    @FXML
    private Button btnPasswordReset;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtSearch;

    ArrayList<User> users = new ArrayList<>();
    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.USER);
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);
    ObservableList<User> observableUserList = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        getAllUsers();
    }

    private void getAllUsers() throws IOException {
        List<User> userList = userBo.getUserList();
        users = (ArrayList<User>) userList;
        observableUserList.addAll(users);
    }

    private void searchUsername() {
        String search = txtSearch.getText();
        boolean found = false;

        for (User user : observableUserList) {
            if (user.getUsername().equalsIgnoreCase(search)) {
                txtEmail.setText(user.getUser_email());
                txtContact.setText(user.getUser_phone());
                txtRole.setText(user.getUser_role());
                found = true;
                break;
            }
        }

        if (!found) {
            txtEmail.clear();
            txtContact.clear();
            txtRole.clear();
            System.out.println("No user found with the provided username.");
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        searchUsername();
    }

    public void btnPasswordResetOnAction(ActionEvent actionEvent) {
        String newPassword = txtNewPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if (newPassword.equals(confirmPassword)) {
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String username = txtSearch.getText();
            for (User user : observableUserList) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    user.setPassword(hashedPassword);
                    if (userDao.updateUser(user)) {
                        new Alert(Alert.AlertType.INFORMATION, "Password updated successfully!").show();
                        clearPasswordFields();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to update the password.").show();
                    }
                    break;
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
        }
    }

    private void clearPasswordFields() {
        txtNewPassword.clear();
        txtConfirmPassword.clear();
    }

}
