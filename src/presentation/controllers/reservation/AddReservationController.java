package presentation.controllers.reservation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AddReservationController {

    @FXML
    private void handleBackToReservationsAction(ActionEvent event) {
        try {
            Parent reservationsPage = FXMLLoader
                    .load(getClass().getResource("../../views/Reservations.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(reservationsPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
