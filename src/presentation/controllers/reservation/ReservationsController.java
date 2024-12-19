package presentation.controllers.reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ReservationsController {

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
    private void handleAddReservationAction(ActionEvent event) {
        System.out.println("Add Reservation");
        try {
            Parent addReservationPage = FXMLLoader.load(getClass().getResource("../../views/AddReservation.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(addReservationPage);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
