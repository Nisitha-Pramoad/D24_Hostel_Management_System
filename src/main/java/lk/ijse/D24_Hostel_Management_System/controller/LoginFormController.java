package lk.ijse.D24_Hostel_Management_System.controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXCheckBox chkbxRememberMe;

    @FXML
    private Label lblForgotYourPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Check if the entered username and password match the expected values
        if (username.equals("nn") && password.equals("9900")) {
            // Successful login, you can navigate to another scene or show a success message here
            openDashboardForm(actionEvent);
            System.out.println("Login successful!");
        } else {
            // Incorrect username or password, you can display an error message here
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    @FXML
    void navigate(MouseEvent event) {

    }

    private void openDashboardForm(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard-form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();
        currentStage.close();
    }
}
