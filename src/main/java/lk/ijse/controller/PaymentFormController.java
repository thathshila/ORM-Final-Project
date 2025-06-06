package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.Custom.PaymentBo;
import lk.ijse.bo.Custom.StudentBo;
import lk.ijse.bo.Custom.StudentCourseBo;
import lk.ijse.dao.Custom.PaymentDao;
import lk.ijse.dao.Custom.StudentCourseDao;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dto.PaymentDto;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;
import lk.ijse.entity.Student_Course;
import lk.ijse.entityTm.PaymentTm;
import lk.ijse.util.Regex;
import lk.ijse.util.TextFieldType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lk.ijse.util.TextFieldType.PRICE;

public class PaymentFormController {

    @FXML
    private AnchorPane anpPayment;

    @FXML
    private TableColumn<?, ?> colBalancePay;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colBtnRemove;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colUpfrontPay;

    @FXML
    private TableColumn<?, ?> colStudentCourseDetailId;

    @FXML
    private ComboBox<String> comboCourses;

    @FXML
    private ComboBox<String> comboStudent;

    @FXML
    private TableView<PaymentTm> tblPayment;

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
    private TextField txtStuCouDetail;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> comboPayHistory;

    @FXML
    private Label lblBalanceAmount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblUpfrontAmount;

    StudentCourseDao studentCourseDao = (StudentCourseDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.STUDENT_COURSE);
    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.STUDENT);
    ArrayList<Student> studentArrayList = new ArrayList<>();
    PaymentDao paymentDao = (PaymentDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.PAYMENT);
    ArrayList<Student_Course> studentCourseArrayList = new ArrayList<>();
    StudentCourseBo studentCourseBo = (StudentCourseBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.STUDENT_COURSE);
    ObservableList<String> studentObservableList = FXCollections.observableArrayList();
    ObservableList<PaymentTm> paymentTmObservableList = FXCollections.observableArrayList();
    PaymentBo paymentBo = (PaymentBo) BoFactory.getBoFactory().getBoType(BoFactory.BoType.PAYMENT);
    ArrayList<Payment> paymentArrayList = new ArrayList<>();
    ObservableList<String> paymentObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        generateNewId();
        getAllStudentCourses();
        getAllStudent();
        getAllPayment();
        searchStudent();
        searchPayment();
        setDate();
        setCellValueFactory();
    }

    private void searchPayment() {
        for (Payment payment : paymentArrayList) {
            paymentObservableList.add(String.valueOf(payment.getStudent_course().getStudent_course_id()));
        }
        comboPayHistory.setItems(paymentObservableList);
    }

    private void getAllPayment() throws IOException {
        List<Payment> paymentList = paymentBo.getPaymentList();
        paymentArrayList = (ArrayList<Payment>) paymentList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("cou_id"));
        colUpfrontPay.setCellValueFactory(new PropertyValueFactory<>("upfront_amount"));
        colBalancePay.setCellValueFactory(new PropertyValueFactory<>("balance_amount"));
        colStudentCourseDetailId.setCellValueFactory(new PropertyValueFactory<>("student_course_id"));
        colBtnRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getAllStudent() throws IOException {
        List<Student> studentList = studentBo.getStudentList();
        studentArrayList = (ArrayList<Student>) studentList;
    }

    private void searchStudent() {
        for (Student student : studentArrayList) {
            studentObservableList.add(student.getStu_id());
        }
        comboStudent.setItems(studentObservableList);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void getAllStudentCourses() throws IOException {
        List<Student_Course> studentCourseList = studentCourseBo.getStudentCourseList();
        studentCourseArrayList = (ArrayList<Student_Course>) studentCourseList;
    }

    private String generateNewId() throws IOException {
        String nextId = paymentDao.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) throws IOException {
        PaymentTm selectedPayment = tblPayment.getSelectionModel().getSelectedItem();

        if (selectedPayment == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment to save").show();
            return;
        }

        Student_Course studentCourse = studentCourseDao.getStudentCourseById(Long.valueOf(txtStuCouDetail.getText()));

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPay_id(txtId.getText());
        paymentDto.setStatus(txtStatus.getText());
        paymentDto.setBalance_amount(selectedPayment.getBalance_amount()); // Use updated balance
        paymentDto.setPay_amount(Double.parseDouble(txtPayAmount.getText()));
        paymentDto.setPay_date(txtDate.getText());
        paymentDto.setStudent_course(studentCourse);

        paymentBo.savePayment(paymentDto);

        new Alert(Alert.AlertType.INFORMATION, "Payment saved successfully").show();

        if (selectedPayment.getBalance_amount() == 0) {
            new Alert(Alert.AlertType.INFORMATION, "Course fee is fully paid!").show();
        }

        getAllPayment();
    }


    @FXML
    void comboCoursesOnAction(ActionEvent event) throws IOException {
        String selectedCourseName = comboCourses.getValue();
        String selectedStudentId = comboStudent.getValue();

        for (Student_Course studentCourse : studentCourseArrayList) {

            if (selectedStudentId != null && selectedCourseName != null &&
                    selectedStudentId.equals(studentCourse.getStudent().getStu_id()) &&
                    selectedCourseName.equals(studentCourse.getCourse().getCourse_name())) {

                txtCoursefee.setText(String.valueOf(studentCourse.getCourse().getCourse_fee()));
                txtStuCouDetail.setText(String.valueOf(studentCourse.getStudent_course_id()));
                break;
            }
        }
    }

    @FXML
    void txtPayAmountOnAction(ActionEvent event) {
        txtStatus.requestFocus();
    }

    @FXML
    void txtStatusOnAction(ActionEvent event) {
        txtPayAmount.requestFocus();
    }

    @FXML
    public void btnConfirmOnAction(ActionEvent actionEvent) {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR,"Please Check TextFields!").show();
            return;
        }
        if (txtId.getText().isEmpty() || comboCourses.getValue() == null || txtPayAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill in all required fields").show();
            return;
        }

        try {
            double payAmount = Double.parseDouble(txtPayAmount.getText());
            long studentCourseId = Long.parseLong(txtStuCouDetail.getText());

            double currentBalance = getCurrentBalance(studentCourseId);

            if (payAmount > currentBalance) {
                new Alert(Alert.AlertType.WARNING, "Payment exceeds the remaining balance").show();
                return;
            }

            double newBalance = currentBalance - payAmount;

            Button btnRemove = new Button("Remove");
            btnRemove.setCursor(Cursor.HAND);

            btnRemove.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove?", yes, no).showAndWait();
                if (type.orElse(no) == yes) {
                    int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                    if (selectedIndex >= 0) {
                        paymentTmObservableList.remove(selectedIndex);
                        tblPayment.refresh();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Please select an item to remove").show();
                    }
                }
            });

            PaymentTm paymentTm = new PaymentTm(
                    txtId.getText(),
                    txtStatus.getText(),
                    payAmount,
                    newBalance,
                    comboStudent.getValue(),
                    comboCourses.getValue(),
                    studentCourseId,
                    btnRemove
            );

            paymentTmObservableList.add(paymentTm);
            tblPayment.setItems(paymentTmObservableList);
            tblPayment.refresh();

            updateStudentCourseBalance(studentCourseId, newBalance);

            if (newBalance == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Course fee fully paid!").show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter valid numbers for payment and course fee.").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    private double getCurrentBalance(long studentCourseId) {
        double courseFee = Double.parseDouble(txtCoursefee.getText());
        double totalPaidAmount = 0;

        for (Payment payment : paymentArrayList) {
            if (payment.getStudent_course().getStudent_course_id() == studentCourseId) {
                totalPaidAmount += payment.getPay_amount();
            }
        }
        double remainingBalance = courseFee - totalPaidAmount;
        return Math.max(remainingBalance, 0);
    }


    private void updateStudentCourseBalance(long studentCourseId, double newBalance) {
        for (Payment payment : paymentArrayList) {
            if (payment.getStudent_course().getStudent_course_id() == studentCourseId) {
                payment.setBalance_amount(newBalance);
                break;
            }
        }
    }


    @FXML
    public void comboStudentOnAction(ActionEvent actionEvent) {
        String studentId = comboStudent.getValue();
        ObservableList<String> studentCourseObservableList = FXCollections.observableArrayList();

        for (Student_Course studentCourse : studentCourseArrayList) {
            if (studentCourse.getStudent().getStu_id().equals(studentId)) {
                studentCourseObservableList.add(studentCourse.getCourse().getCourse_name());
            }
        }
        comboCourses.setItems(studentCourseObservableList);
        if (!studentCourseObservableList.isEmpty()) {
            comboCourses.setValue(studentCourseObservableList.get(0));
        }
    }

    @FXML
    public void comboPayHistoryOnAction(ActionEvent actionEvent) {
        Long stu_cou_id = Long.valueOf(comboPayHistory.getValue());

        for (Payment payment : paymentArrayList) {
            if (payment.getStudent_course().getStudent_course_id().equals(stu_cou_id)) {
                lblBalanceAmount.setText(String.valueOf(payment.getBalance_amount()));
                lblDate.setText(payment.getPay_date());
                lblStatus.setText(payment.getStatus());
                lblUpfrontAmount.setText(String.valueOf(payment.getUpfront_amount()));
            }
        }
    }

    public void txtPayAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFieldType.PRICE, txtPayAmount);
    }

    public boolean isValidated() {
        if (!Regex.setTextColor(TextFieldType.PRICE, txtPayAmount)) return false;
        return true;
    }
}

