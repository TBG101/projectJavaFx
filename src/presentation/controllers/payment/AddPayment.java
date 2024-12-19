package presentation.controllers.payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class AddPayment {
    @FXML
    private void handleBackToPayment(ActionEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("../../views/Payment.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(homePage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
