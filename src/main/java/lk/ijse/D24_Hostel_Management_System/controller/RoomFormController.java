package lk.ijse.D24_Hostel_Management_System.controller;

        import javafx.application.Platform;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.TableView;
        import javafx.scene.control.TextField;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;

public class RoomFormController {

    @FXML
    private ComboBox<?> cmbRoomType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblRoomManagement;

    @FXML
    private TextField txtAmenitiesAndFeatures;

    @FXML
    private TextField txtMaximumOccupency;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRoomDescription;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtRoomSize;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

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

}
