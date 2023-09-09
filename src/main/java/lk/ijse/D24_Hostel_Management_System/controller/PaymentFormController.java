package lk.ijse.D24_Hostel_Management_System.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel_Management_System.dto.PaymentDto;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.repository.PaymentRepository;
import lk.ijse.D24_Hostel_Management_System.repository.impl.PaymentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.PaymentService;
import lk.ijse.D24_Hostel_Management_System.service.RoomService;
import lk.ijse.D24_Hostel_Management_System.service.impl.PaymentServiceImpl;
import lk.ijse.D24_Hostel_Management_System.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel_Management_System.tdm.PaymentHistoryTM;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbMonth;

    @FXML
    private ComboBox<String> cmbPaidOrNot;

    @FXML
    private TableColumn<PaymentHistoryTM, String> colAmount;

    @FXML
    private TableColumn<PaymentHistoryTM, String> colPaymentDate;

    @FXML
    private TableColumn<PaymentHistoryTM, String> colPaymentHistoryId;

    @FXML
    private TableColumn<PaymentHistoryTM, String> colPaymentMethod;

    @FXML
    private TableColumn<PaymentHistoryTM, String> colStudentId;

    @FXML
    private ImageView imgHome;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PaymentHistoryTM> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtSearchStudentId;

    @FXML
    private TextField txtStudentName;

    private final PaymentService paymentService;

    public PaymentFormController() {
        paymentService = PaymentServiceImpl.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> monthList = FXCollections.observableArrayList(
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        );
        cmbMonth.setItems(monthList);

        ObservableList<String> paidOrNotList = FXCollections.observableArrayList(
                "Paid",
                "Not Paid"
        );
        cmbPaidOrNot.setItems(paidOrNotList);

        loadAllPayments();
    }


    @FXML
    void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/dashboard-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    @FXML
    void loadAllPayments() {
        try {
            // Retrieve the list of payment history from the PaymentService
            List<PaymentHistoryTM> paymentList = paymentService.loadAllPayments();

            // Create an ObservableList to hold PaymentHistoryTM objects
            ObservableList<PaymentHistoryTM> observablePaymentList = FXCollections.observableArrayList();

            // Convert PaymentDto objects to PaymentHistoryTM objects and add them to the list
            for (PaymentHistoryTM paymentTM : paymentList) {
                PaymentHistoryTM paymentHistoryTM = new PaymentHistoryTM();
                paymentHistoryTM.setPaymentHistoryId(paymentTM.getPaymentHistoryId());
                paymentHistoryTM.setStudent(paymentTM.getStudent());
                paymentHistoryTM.setPaymentDate(paymentTM.getPaymentDate()); // Assuming you have a getDate method in PaymentDto
                paymentHistoryTM.setAmount(paymentTM.getAmount());
                paymentHistoryTM.setPaymentMethod(paymentTM.getPaymentMethod()); // Assuming you have a getPaymentMethod method in PaymentDto

                observablePaymentList.add(paymentHistoryTM);
            }

            // Set the items of the TableView to display the payment data
            tblPayment.setItems(observablePaymentList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load payments: " + e.getMessage()).show();
        }
    }



    @FXML
    void btnFindOnAction(ActionEvent event) {
        String studentIdOrNationalId = txtSearchStudentId.getText();

        // Use the PaymentService to find the student
        StudentDto student = paymentService.findStudentByIdOrNationalId(studentIdOrNationalId);

        if (student != null) {
            // Student found, display a confirmation message
            String studentName = student.getFullName();
            txtStudentName.setText(studentName);

            new Alert(Alert.AlertType.CONFIRMATION, "Student is Found").show();
        } else {
            // Student not found, display an error message
            new Alert(Alert.AlertType.ERROR, "No student found with the provided ID or national ID.").show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String selectedMonth = cmbMonth.getValue(); // Get the selected month
        String paymentAmount = txtAmount.getText().trim();

        if (selectedMonth.isEmpty() || paymentAmount.isEmpty()) {
            // Validate that both month and payment amount are provided
            new Alert(Alert.AlertType.INFORMATION, "Please select a month and provide the payment amount.").show();
        } else {
            // Create a new Payment entity or DTO with the selected month and payment amount
            PaymentDto payment = new PaymentDto();
            payment.setPaymentId("P001");
            payment.setMonth(selectedMonth);
            payment.setAmount(Double.parseDouble(paymentAmount));

            // Get the student ID or national ID from the text field
            String studentIdOrNationalId = txtSearchStudentId.getText().trim();

            // Use the PaymentService to find the student
            StudentDto student = paymentService.findStudentByIdOrNationalId(studentIdOrNationalId);

            if (student != null) {
                // Set the student for the payment
                payment.setStudent(student);

                // You should implement a method to perform this operation
                String saved = paymentService.savePayment(payment);

                if (saved != null) {
                    loadAllPayments();
                    // After successfully saving the payment, you can display a success message
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment information has been saved successfully.").show();

                    // Optionally, you can clear the input fields for the next payment
                    cmbMonth.setValue(null);
                    txtAmount.clear();
                    txtSearchStudentId.clear();
                    txtStudentName.clear();
                } else {
                    // Handle the case where payment saving failed
                    new Alert(Alert.AlertType.ERROR, "Failed to save payment information.").show();
                }
            } else {
                // Student not found, display an error message
                new Alert(Alert.AlertType.ERROR, "No student found with the provided ID or national ID.").show();
            }
        }
    }





    private StudentDto findStudent(String studentIdOrNationalId) {
        // You should replace this with your actual implementation
        // This method should return the student DTO if found, or null if not found
        // You might need to call your service or repository to search for the student
        return null;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String paymentId = "P001";

        boolean deleted = paymentService.deletePayment(paymentId);

        if (deleted) {
            // Successfully deleted
            loadAllPayments();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment has been deleted.").show();
        } else {
            // Payment not found or deletion failed
            new Alert(Alert.AlertType.ERROR, "Failed to delete payment.").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String selectedMonth = cmbMonth.getValue(); // Get the selected month
        String paymentAmount = txtAmount.getText().trim();

        if (selectedMonth.isEmpty() || paymentAmount.isEmpty()) {
            // Validate that both month and payment amount are provided
            new Alert(Alert.AlertType.INFORMATION, "Please select a month and provide the payment amount.").show();
        } else {
            // Create a new Payment entity or DTO with the selected month and payment amount
            PaymentDto payment = new PaymentDto();
            payment.setPaymentId("P001");
            payment.setMonth(selectedMonth);
            payment.setAmount(Double.parseDouble(paymentAmount));

            // Get the student ID or national ID from the text field
            String studentIdOrNationalId = txtSearchStudentId.getText().trim();

            // Use the PaymentService to find the student
            StudentDto student = paymentService.findStudentByIdOrNationalId(studentIdOrNationalId);

            if (student != null) {
                // Set the student for the payment
                payment.setStudent(student);

                // You should implement the updatePayment method to perform this operation
                boolean updated = paymentService.updatePayment(payment);

                if (updated) {
                    loadAllPayments();
                    // After successfully updating the payment, you can display a success message
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment information has been updated successfully.").show();

                    // Optionally, you can clear the input fields for the next payment
                    cmbMonth.setValue(null);
                    txtAmount.clear();
                    txtSearchStudentId.clear();
                    txtStudentName.clear();
                } else {
                    // Handle the case where payment updating failed
                    new Alert(Alert.AlertType.ERROR, "Failed to update payment information.").show();
                }
            } else {
                // Student not found, display an error message
                new Alert(Alert.AlertType.ERROR, "No student found with the provided ID or national ID.").show();
            }
        }
    }


    @FXML
    void btnFilterOnAction(ActionEvent event) {

    }



}
