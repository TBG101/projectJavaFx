package dao;

import entities.Customer;
import entities.Payment;
import entities.Room;
import entities.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IGestionReservation {
    void addReservation(Reservation reservation) throws SQLException;
    void updateReservation(Reservation reservation) throws SQLException;
    void deleteReservation(int reservationId) throws SQLException;
    Reservation getReservationById(int reservationId) throws SQLException;
    List<Reservation> getAllReservations() throws SQLException;

}