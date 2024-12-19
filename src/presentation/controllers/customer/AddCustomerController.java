package presentation.controllers.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AddCustomerController {
    @FXML
    private void handleBackToCustomer(ActionEvent event) {
        try {
            Parent CustomerPage = FXMLLoader
                    .load(getClass().getResource("../../views/Customer.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(CustomerPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
