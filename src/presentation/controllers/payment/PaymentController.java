package presentation.controllers.payment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PaymentController {

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
    private void handleAddPayment(ActionEvent event) {
        try {
            Parent addPaymentPage = FXMLLoader.load(getClass().getResource("../../views/AddPayment.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(addPaymentPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
