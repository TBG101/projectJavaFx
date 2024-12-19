package presentation.controllers.reservation;

import entities.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.Initializable;

public class ReservationsController implements Initializable {

    @FXML
    private TableView<Reservation> reservationTable;
    @FXML
    private TableColumn<Reservation, Integer> colReservationId;
    @FXML
    private TableColumn<Reservation, Integer> colCustomerId;
    @FXML
    private TableColumn<Reservation, Integer> colRoomNumber;
    @FXML
    private TableColumn<Reservation, String> colCheckInDate;
    @FXML
    private TableColumn<Reservation, String> colCheckOutDate;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing ReservationsController...");
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        try {
            loadReservations();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadReservations() throws SQLException {
        System.out.println("Loading reservations...");
        Reservation reservation = new Reservation();
        List<Reservation> reservations = reservation.getAllReservations();
        System.out.println("Number of reservations loaded: " + reservations.size());
        ObservableList<Reservation> reservationList = FXCollections.observableArrayList(reservations);
        reservationTable.setItems(reservationList);
    }

    @FXML
    private void handleBackToHomeAction(ActionEvent event) {
        try {
            
             homePage = FXMLLoader.load(getClass().getResource("../../views/Home.fxml"));
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
