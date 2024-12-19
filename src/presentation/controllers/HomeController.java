package presentation.controllers;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class HomeController {

    @FXML
    private void handleReservationsButtonAction(ActionEvent event) {
        System.out.println("reservation");
        try {
            Parent reservationsPage = FXMLLoader.load(getClass().getResource("../views/Reservations.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(reservationsPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRoomsAction(ActionEvent event) {
        try {
            Parent roomsPage = FXMLLoader.load(getClass().getResource("../views/Rooms.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(roomsPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCustomerAction(ActionEvent event) {
        try {
            Parent customerPage = FXMLLoader.load(getClass().getResource("../views/Customer.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(customerPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePaymentAction(ActionEvent event) {
        try {
            Parent paymentPage = FXMLLoader.load(getClass().getResource("../views/Payment.fxml"));
            Scene stage = ((Node) event.getSource()).getScene();
            stage.setRoot(paymentPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
