package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Bo.BoFactory;
import lk.ijse.Bo.Custom.StudentCourseBo;
import lk.ijse.Dao.Custom.PaymentDao;
import lk.ijse.Dao.DaoFactory;
import lk.ijse.Entity.Course;
import lk.ijse.Entity.Student;
import lk.ijse.Entity.Student_Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormController {

    @FXML
    private AnchorPane anpPayment;

    @FXML
    private TableColumn<?, ?> colBalancePay;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colUpfrontPay;

    @FXML
    private ComboBox<String> comboCourses;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtCoursefee;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPayAmount;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtStudentId;

    @FXML
    private Button btnSave;

    PaymentDao paymentDao = (PaymentDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.PAYMENT);
    ArrayList<Student_Course> studentCourseArrayList = new ArrayList<>();
    StudentCourseBo studentCourseBo = (StudentCourseBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.STUDENT_COURSE);

    public void initialize() throws IOException {
        generateNewId();
        getAllStudentCourses();
      //  setCourseId();
    }

    private void getAllStudentCourses() throws IOException {
        List<Student_Course> studentCourseList = studentCourseBo.getStudentCourseList();
        studentCourseArrayList = (ArrayList<Student_Course>) studentCourseList;
    }

//    private void setCourseId() {
//        ObservableList<String> courseIds = FXCollections.observableArrayList();
//        String studentId = txtStudentId.getText();
//
//        for (Student_Course studentCourse : studentCourseArrayList) {
//            // Check if the student's ID matches the entered studentId
//            if (studentCourse.getStudent().getStu_id().equals(studentId)) {
//                // Add the course ID to the list if there's a match
//                courseIds.add(studentCourse.getCourse().getCourse_id());
//            }
//        }
//        // Set the list of course IDs in the ComboBox
//        comboCourses.setItems(courseIds);
//    }

    private String generateNewId() throws IOException {
        String nextId = paymentDao.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void comboCoursesOnAction(ActionEvent event) {

    }

    @FXML
    void txtDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtPayAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtStatusOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        //setCourseId();
    }

}
