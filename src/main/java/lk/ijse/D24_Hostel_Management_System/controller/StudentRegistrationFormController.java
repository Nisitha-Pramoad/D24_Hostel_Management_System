package lk.ijse.D24_Hostel_Management_System.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StudentRegistrationFormController {

    @FXML
    private CheckBox chckbxTermsAndCondition;

    @FXML
    private ComboBox<?> cmbGender;

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



}
