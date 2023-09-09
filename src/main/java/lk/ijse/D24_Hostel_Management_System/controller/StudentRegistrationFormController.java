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
import lk.ijse.D24_Hostel_Management_System.service.RoomService;
import lk.ijse.D24_Hostel_Management_System.service.StudentService;
import lk.ijse.D24_Hostel_Management_System.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel_Management_System.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel_Management_System.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentRegistrationFormController implements Initializable {

    @FXML
    public TableView<StudentTM> tblStudents;

    @FXML
    private TableColumn<StudentTM, String> Conditions;

    @FXML
    private TableColumn<StudentTM, String> colAddress;

    @FXML
    private TableColumn<StudentTM, String> colCampus;

    @FXML
    private TableColumn<StudentTM, String> colContact;

    @FXML
    private TableColumn<StudentTM, String> colDob;

    @FXML
    private TableColumn<StudentTM, String> colEmail;

    @FXML
    private TableColumn<StudentTM, String> colFName;

    @FXML
    private TableColumn<StudentTM, String> colGender;

    @FXML
    private TableColumn<StudentTM, String> colId;

    @FXML
    private TableColumn<StudentTM, String> colKeyMoney;

    @FXML
    private TableColumn<StudentTM, String> colLName;

    @FXML
    private TableColumn<StudentTM, String> colNId;

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
    private ComboBox<String> roomType;

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

    private final RoomService roomService;

    public StudentRegistrationFormController() {
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> genderTypeList = FXCollections.observableArrayList(
                "male",
                "female"
        );
        cmbGender.setItems(genderTypeList);

        ObservableList<String> roomTypeList = FXCollections.observableArrayList(
                "Non-Ac",
                "Non-Ac/Food",
                "Ac",
                "Ac/Food"
        );
        roomType.setItems(roomTypeList);

        Conditions.setCellValueFactory(new PropertyValueFactory<>("acceptConditions"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCampus.setCellValueFactory(new PropertyValueFactory<>("campusName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFName.setCellValueFactory(new PropertyValueFactory<>("nameIdentifierFirstName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        colLName.setCellValueFactory(new PropertyValueFactory<>("nameIdentifierLastName"));
        colNId.setCellValueFactory(new PropertyValueFactory<>("nationalId"));

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
                String formattedKeyMoney = String.format("%.2f", keyMoneyValue);
                txtKeymoney.setText(formattedKeyMoney);
                chckbxTermsAndCondition.setSelected(newValue.isAcceptConditions());
            }
        });

        loadAllCustomers();
    }


    private void loadAllCustomers() {
        try {
            StudentService studentService = new StudentServiceImpl().getInstance();
            List<StudentDto> studentList = studentService.getAllStudents();

            ObservableList<StudentTM> observableStudentList = FXCollections.observableArrayList(convertToStudentTMList(studentList));

            tblStudents.setItems(observableStudentList);

            // Print the loaded students to the console
            /*for (StudentDto student : studentList) {
                System.out.println("Student ID: " + student.getStudentId());
                System.out.println("Name: " + student.getNameIdentifier().getFirstName() + " " + student.getNameIdentifier().getLastName());
                System.out.println("Date of Birth: " + student.getDateOfBirth());
                System.out.println("National ID: " + student.getNationalId());
                System.out.println("Gender: " + student.getGender());
                System.out.println("Address: " + student.getAddress());
                System.out.println("Contact: " + student.getContact());
                System.out.println("Email: " + student.getEmail());
                System.out.println("Campus: " + student.getCampusName());
                System.out.println("Key Money: " + student.getKeymoney());
                System.out.println("Accepted Conditions: " + student.isAcceptCondions());
                System.out.println("Created Date Time: " + student.getCreatedDateTime());
                System.out.println("----------------------------------");
            }*/
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load students: " + e.getMessage()).show();
        }
    }


    // Helper method to convert StudentDto objects to StudentTM objects
    private List<StudentTM> convertToStudentTMList(List<StudentDto> studentDtoList) {
        List<StudentTM> studentTMList = new ArrayList<>();
        for (StudentDto studentDto : studentDtoList) {
            StudentTM studentTM = new StudentTM();
            studentTM.setStudentId(studentDto.getStudentId());

            NameIdentifier nameIdentifier = studentDto.getNameIdentifier();
            if (nameIdentifier != null) {
                studentTM.setNameIdentifier(nameIdentifier);
            }

            studentTM.setDateOfBirth(studentDto.getDateOfBirth());
            studentTM.setNationalId(studentDto.getNationalId());
            studentTM.setGender(studentDto.getGender());
            studentTM.setAddress(studentDto.getAddress());
            studentTM.setContact(studentDto.getContact());
            studentTM.setEmail(studentDto.getEmail());
            studentTM.setCampusName(studentDto.getCampusName());
            studentTM.setKeymoney(studentDto.getKeymoney());
            studentTM.setAcceptConditions(studentDto.isAcceptCondions());
            studentTM.setCreatedDateTime(studentDto.getCreatedDateTime());

            studentTMList.add(studentTM);
        }
        return studentTMList;
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
        String roomTypeName = roomType.getValue();
        LocalDate startDate = dtpkStartDate.getValue();
        LocalDate endDate = dtpkEndDate.getValue();

        // Check room availability
        boolean isRoomAvailable = roomService.isRoomAvailable(roomTypeName, startDate, endDate);

        if (isRoomAvailable) {
            StudentService studentService = new StudentServiceImpl().getInstance();
            String isSavedStudentId = studentService.saveStudent(student);

            if (isSavedStudentId != null) {
                loadAllCustomers();
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save student. Please try again.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No available rooms for the selected date and room type.").show();
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        StudentTM selectedStudent = tblStudents.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a student to delete.").show();
            return;
        }

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText("Confirm Deletion");
        confirmationAlert.setContentText("Are you sure you want to delete this student?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String studentId = selectedStudent.getStudentId();

            StudentService studentService = new StudentServiceImpl().getInstance();
            boolean isDeleted = studentService.deleteStudent(studentId);

            if (isDeleted) {
                loadAllCustomers();
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully!").show();
                loadAllCustomers();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete student. Please try again.").show();
            }
        }
    }

    @FXML
    void btnSearchAvailableOnAction(ActionEvent event) {
        // Implement search logic here
        String roomTypeName = roomType.getValue();
        LocalDate startDate = dtpkStartDate.getValue();
        LocalDate endDate = dtpkEndDate.getValue();

        // Check room availability
        boolean isRoomAvailable = roomService.isRoomAvailable(roomTypeName, startDate, endDate);
        if (isRoomAvailable) {
            // Show confirmation message for room availability
            new Alert(Alert.AlertType.CONFIRMATION, "Room is available. Student can be registered.").show();
        } else {
            // Show error message for no available rooms
            new Alert(Alert.AlertType.ERROR, "No available rooms for the selected date and room type.").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        StudentDto studentDto = getStudent();

        StudentService studentService = new StudentServiceImpl().getInstance();
        boolean isUpdated = studentService.updateStudent(studentDto);

        if (isUpdated) {
            loadAllCustomers();
            new Alert(Alert.AlertType.CONFIRMATION, "Student updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update student. Please try again.").show();
        }
    }

    private StudentDto getStudent() {
        String studentId = txtStudentId.getText();
        String dateOfBirth = String.valueOf(dtpkBirthday.getValue());
        String nationalId = txtNationalId.getText();
        String gender = cmbGender.getValue();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
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
        student.setGender(gender);
        student.setAddress(address);
        student.setContact(contact);
        student.setEmail(email);
        student.setCampusName(campusName);
        student.setKeymoney(keyMoney);
        student.setAcceptCondions(isCheckedCondition);

        return student;
    }

    private NameIdentifier getNameIdentifier() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();

        NameIdentifier nameIdentifier = new NameIdentifier();
        nameIdentifier.setFirstName(firstName);
        nameIdentifier.setLastName(lastName);

        return nameIdentifier;
    }
}
