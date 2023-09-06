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
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.embedded.NameIdentifier;
import lk.ijse.D24_Hostel_Management_System.entity.Student;
import lk.ijse.D24_Hostel_Management_System.repository.impl.StudentRepositoryImpl;
import lk.ijse.D24_Hostel_Management_System.service.StudentService;
import lk.ijse.D24_Hostel_Management_System.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRegistrationFormController implements Initializable {

    @FXML
    public TableView<StudentTM> tblStudents;

    @FXML
    private CheckBox chckbxTermsAndCondition;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private DatePicker dtpkBirthday;

    @FXML
    private DatePicker dtpkEndDate;

    @FXML
    private DatePicker dtpkStartDate;

    @FXML
    private ImageView imgHome;

    @FXML
    private ImageView imgStudentsDetailsPage;

    @FXML
    private ComboBox<?> roomType;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCampus;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtKeymoney;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNationalId;

    @FXML
    private TextField txtStudentId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> genderTypeList = FXCollections.observableArrayList(
                "male",
                "female"
        );
        cmbGender.setItems(genderTypeList);

        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("nameIdentifier"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudents.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudents.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("campusName"));
        tblStudents.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        tblStudents.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("acceptCondions"));
        tblStudents.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("createdDateTime"));

        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtFirstName.setText(newValue.getNameIdentifier().getFirstName());
                txtLastName.setText(newValue.getNameIdentifier().getLastName());
                dtpkBirthday.setValue(newValue.getDateOfBirth());
                txtNationalId.setText(newValue.getNationalId());
                cmbGender.setValue(newValue.getGender());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtEmail.setText(newValue.getEmail());
                txtCampus.setText(newValue.getCampusName());
                double keyMoneyValue = newValue.getKeymoney();
                String formattedKeyMoney = String.format("%.2f", keyMoneyValue); // Format as a decimal with 2 decimal places
                txtKeymoney.setText(formattedKeyMoney);
                chckbxTermsAndCondition.setSelected(newValue.isAcceptCondions());


                /*txtStudentId.setDisable(false);
                txtFirstName.setDisable(false);
                txtLastName.setDisable(false);*/
                loadAllCustomers();
            }
        });

        //set new sid
        //txtStudentId.setText(getNewStudentId());
        //loadAllCustomers();
    }

    private void loadAllCustomers() {
        /*tblStudents.getItems().clear();
        *//*Get all customers*//*

        try {
            // Fetch student data from your StudentRepository
            StudentRepositoryImpl studentRepositoryImpl = new StudentRepositoryImpl();
            List<StudentTM> studentList = studentRepositoryImpl.getAllStudents(); // Implement this method in StudentRepository

            *//*for (StudentTM s : studentList) {
                tblStudents.getItems().add(new StudentTM(s.getStudentId(), s.getNameIdentifier(), s.getDateOfBirth(), s.getNationalId(),
                        s.getGender(), s.getAddress(), s.getContact(), s.getEmail(), s.getCampusName(), s.getKeymoney(),
                        s.isAcceptCondions()));
            }*//*

            // Convert the list to an ObservableList for use with TableView
            ObservableList<StudentTM> observableStudentList = FXCollections.observableArrayList(studentList);

            // Set the table's data source to the observable list
            tblStudents.setItems(observableStudentList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
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
    void btnSaveOnAction(ActionEvent event) {
        StudentDto student = getStudent();

        StudentService studentService = new StudentServiceImpl().getInstance();
        String isSavedStudentId = studentService.saveStudent(student);

        if (isSavedStudentId != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save student. Please try again.").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnPhotoChooser(ActionEvent event) {

    }

    @FXML
    void btnSearchAvailableOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private StudentDto getStudent() {
        String studentId = txtStudentId.getText();
        String dateOfBirth = String.valueOf(dtpkBirthday.getValue());
        String nationalId = txtNationalId.getText();
        String Gender = cmbGender.getValue();
        String address = txtAddress.getText();
        String contact = txtAddress.getText();
        String email = txtEmail.getText();
        String campusName = txtCampus.getText();
        double keyMoney = Double.parseDouble(txtKeymoney.getText());
        boolean isCheckedCondition = chckbxTermsAndCondition.isSelected();

        StudentDto student = new StudentDto();
        student.setStudentId(studentId);

        NameIdentifier nameIdentifier = getNameIdentifier();
        student.setNameIdentifier(nameIdentifier);

        student.setDateOfBirth(LocalDate.parse(dateOfBirth));
        student.setNationalId(nationalId);
        student.setGender(Gender);
        student.setAddress(address);
        student.setContact(contact);
        student.setEmail(email);
        student.setCampusName(campusName);
        student.setKeymoney(keyMoney);
        student.setAcceptCondions(isCheckedCondition);

        return student;
    }

    private NameIdentifier getNameIdentifier(){
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();

        NameIdentifier nameIdentifier = new NameIdentifier();
        nameIdentifier.setFirstName(firstName);
        nameIdentifier.setLastName(lastName);

        return nameIdentifier;
    }

    private String getNewStudentId() {
        try {
            // Fetch the new student ID from the service
            StudentService studentService = new StudentServiceImpl().getInstance();
            return studentService.getGenerateStudentId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate student ID").show();
            return null; // Handle the error as needed
        }
    }

}
