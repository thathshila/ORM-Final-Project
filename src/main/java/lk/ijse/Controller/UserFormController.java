package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Bo.BoFactory;
import lk.ijse.Bo.Custom.UserBo;
import lk.ijse.Dao.Custom.UserDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Dto.UserDto;
import lk.ijse.EntityTm.UserTm;
import lk.ijse.Entity.User;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserFormController {

    @FXML
    private AnchorPane anpUser;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.USER);
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);
    ObservableList<UserTm> userTmObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        setDate();
        setCellValueFactory();
        setTable();
        selectTableRow();
        generateNewId();
        filterUser();
    }

    private void generateNewId() throws IOException {
        String nextId = userDao.getCurrentId();
        txtUserId.setText(nextId);
    }

    private void setTable() throws IOException {
        List<User> userList = userBo.getUserList();
        for(User user : userList){
            UserTm userTm = new UserTm(user.getUser_id(),user.getUsername(),user.getUser_email(),user.getUser_phone(),user.getUser_role(),user.getUser_date());
            userTmObservableList.add(userTm);
        }
        tblUser.setItems(userTmObservableList);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("user_role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("user_date"));
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(mouseEvent -> {
            int row = tblUser.getSelectionModel().getSelectedIndex();
            UserTm userTm = tblUser.getItems().get(row);
            txtUserId.setText(userTm.getUser_id());
            txtUsername.setText(userTm.getUsername());
            txtEmail.setText(userTm.getUser_email());
            txtContact.setText(userTm.getUser_phone());
            txtDate.setText(userTm.getUser_date().toString());
            txtRole.setText(userTm.getUser_role());
        });
    }

    public void clearFields(){
        txtContact.clear();
        txtDate.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtRole.clear();
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
    }

    private void filterUser() {
        FilteredList<UserTm> filterData = new FilteredList<>(userTmObservableList, e -> true);

        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_email().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_id().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_phone().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_role().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<UserTm> userTmSortedList = new SortedList<>(filterData);
        userTmSortedList.comparatorProperty().bind(tblUser.comparatorProperty());
        tblUser.setItems(userTmSortedList);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Date date = Date.valueOf(txtDate.getText());
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        UserDto userDto = new UserDto(id, username, password, email, contact, role, date);
        if(userBo.save(userDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"SQL Error").show();
        }
        setCellValueFactory();
        setTable();
        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        Date date = Date.valueOf(txtDate.getText());
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        UserDto userDto = new UserDto(id, username, password, email, contact, role, date);
        if(userBo.update(userDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
            setCellValueFactory();
            setTable();
            clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes",ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if(result.orElse(no) == yes) {
            if (userBo.delete(txtUserId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
        clearFields();
        setCellValueFactory();
        setTable();
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoleOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

}
