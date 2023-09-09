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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel_Management_System.dto.RoomDto;
import lk.ijse.D24_Hostel_Management_System.dto.StudentDto;
import lk.ijse.D24_Hostel_Management_System.service.RoomService;
import lk.ijse.D24_Hostel_Management_System.service.StudentService;
import lk.ijse.D24_Hostel_Management_System.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel_Management_System.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel_Management_System.tdm.RoomTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {

    @FXML
    private TableColumn<RoomTM, String> colAmenitiesAndFeatures;

    @FXML
    private TableColumn<RoomTM, String> colMaximumOccupancy;

    @FXML
    private TableColumn<RoomTM, String> colPricing;

    @FXML
    private TableColumn<RoomTM, String> colRoomStatus;

    @FXML
    private TableColumn<RoomTM, String> colRoomId;

    @FXML
    private TableColumn<RoomTM, String> colRoomType;

    @FXML
    private TableColumn<RoomTM, String> colSize;

    @FXML
    private ComboBox<String> cmbRoomType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RoomTM> tblRoomManagement;

    @FXML
    private TextField txtAmenitiesAndFeatures;

    @FXML
    private TextField txtMaximumOccupency;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<String> cmbRoomStatus;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtRoomSize;

    private final RoomService roomService;

    public RoomFormController() {
        roomService = RoomServiceImpl.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roomTypeList = FXCollections.observableArrayList(
                "Non-Ac",
                "Non-Ac/Food",
                "Ac",
                "Ac/Food"
        );
        cmbRoomType.setItems(roomTypeList);

        ObservableList<String> roomStatusType = FXCollections.observableArrayList(
                "Active",
                "InActive"
        );
        cmbRoomStatus.setItems(roomStatusType);

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colPricing.setCellValueFactory(new PropertyValueFactory<>("pricing"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("roomSize"));
        colMaximumOccupancy.setCellValueFactory(new PropertyValueFactory<>("maximumOccupency"));
        colAmenitiesAndFeatures.setCellValueFactory(new PropertyValueFactory<>("armentiesAndFeatures"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));

        tblRoomManagement.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomId.setText(newValue.getRoomId());
                cmbRoomType.setValue(newValue.getRoomType());
                txtPrice.setText(Double.toString(newValue.getPricing()));
                txtRoomSize.setText(Double.toString(newValue.getRoomSize()));
                txtMaximumOccupency.setText(Integer.toString(newValue.getMaximumOccupency()));
                txtAmenitiesAndFeatures.setText(newValue.getArmentiesAndFeatures());
                cmbRoomStatus.setValue(newValue.getRoomStatus());
            }
        });
        loadAllRooms();
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
    void loadAllRooms() {
        List<RoomDto> roomDtoList = roomService.getAllRooms();

        ObservableList<RoomTM> roomTMObservableList = FXCollections.observableArrayList();

        for (RoomDto roomDto : roomDtoList) {
            RoomTM roomTM = new RoomTM(
                    roomDto.getRoomId(),
                    roomDto.getRoomType(),
                    roomDto.getPricing(),
                    roomDto.getRoomSize(),
                    roomDto.getMaximumOccupency(),
                    roomDto.getArmentiesAndFeatures(),
                    roomDto.getRoomStatus()
            );
            roomTMObservableList.add(roomTM);
        }

        tblRoomManagement.setItems(roomTMObservableList);
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        RoomDto roomDto = getRoom();

        String savedRoomId = roomService.saveRoom(roomDto);

        if (savedRoomId != null) {
            loadAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Room saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save room. Please try again.").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // Implement the delete action
        String roomIdToDelete = txtRoomId.getText();
        boolean isDeleted = roomService.deleteRoom(roomIdToDelete);

        if (isDeleted) {
            loadAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Room deleted successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete room. Please try again.").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Implement the update action
        RoomDto roomDto = getRoom();
        boolean isUpdated = roomService.updateRoom(roomDto);

        if (isUpdated) {
            loadAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Room updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update room. Please try again.").show();
        }
    }

    // Other methods

    private RoomDto getRoom() {
        String roomId = txtRoomId.getText();
        String roomType = cmbRoomType.getValue();
        double roomPrice = Double.parseDouble(txtPrice.getText());
        double roomSize = Double.parseDouble(txtRoomSize.getText());
        int maximumOccupancy = Integer.parseInt(txtMaximumOccupency.getText());
        String amenitiesAndFeatures = txtAmenitiesAndFeatures.getText();
        String roomStatus = cmbRoomStatus.getValue();

        RoomDto roomDto = new RoomDto(roomId, roomType, roomPrice, roomSize, maximumOccupancy, amenitiesAndFeatures, roomStatus);
        return roomDto;
    }


}
