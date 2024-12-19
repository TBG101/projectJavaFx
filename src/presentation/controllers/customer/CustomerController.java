package presentation.controllers.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CustomerController {

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
    private void handleAddCustomerAction(ActionEvent event) {
        try {
            Parent addCustomerPage = FXMLLoader.load(getClass().getResource("../../views/AddCustomer.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(addCustomerPage);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
