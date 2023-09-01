package lk.ijse.D24_Hostel_Management_System.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeFormController {

    @FXML
    void ToLoginPageOnAction(ActionEvent actionEvent) throws IOException {
        // Get the current stage
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Load the LoginPageForm and create a new stage
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login-form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.setMaximized(true);
        stage.centerOnScreen();
        stage.show();

        // Close the current stage
        currentStage.close();
    }

}
