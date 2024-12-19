package presentation.controllers.room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AddRoom {
    @FXML
    private void handleBackToRoom(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("../../views/Rooms.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(homePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToRoomsAction(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("../../views/Rooms.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(homePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
