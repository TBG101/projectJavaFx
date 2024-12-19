package presentation.controllers.room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RoomController {
    @FXML
    private void handleBackToHomeAction(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("../../views/Home.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(homePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddRoomAction(ActionEvent event) {
        try {
            Parent createRoomPage = FXMLLoader.load(getClass().getResource("../../views/AddRooms.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(createRoomPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
