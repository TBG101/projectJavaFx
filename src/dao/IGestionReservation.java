package dao;

import entities.Customer;
import entities.Payment;
import entities.Room;
import entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IGestionReservation {
    void addReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    void deleteReservation(int reservationId);
    Reservation getReservationById(int reservationId);
    List<Reservation> getAllReservations();

    void setCheckOutDate(LocalDate checkOutDate);
}