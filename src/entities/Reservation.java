package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IGestionReservation;
import dao.SingletonConnection;

public class Reservation implements IGestionReservation {
    private int reservationId;
    private Customer customer;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation() {
    }

    public Reservation(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getCustomerId() {
        return customer.getId();
    }

    public int getRoomNumber() {
        return room.getRoomNumber();
    }

    @Override
    public void addReservation(Reservation reservation) throws SQLException {
        Connection connection = SingletonConnection.getConnection();
        String query = "INSERT INTO reservation (reservationId, checkInDate, checkOutDate, roomId, customerId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservation.getReservationId());
        preparedStatement.setDate(2, java.sql.Date.valueOf(reservation.getCheckInDate()));
        preparedStatement.setDate(3, java.sql.Date.valueOf(reservation.getCheckOutDate()));
        preparedStatement.setInt(4, reservation.getRoom().getRoomNumber());
        preparedStatement.setInt(5, reservation.getCustomer().getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateReservation(Reservation reservation) throws SQLException {
        Connection connection = SingletonConnection.getConnection();
        String query = "UPDATE reservation SET checkInDate = ?, checkOutDate = ?, roomId = ?, customerId = ? WHERE reservationId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, java.sql.Date.valueOf(reservation.getCheckInDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(reservation.getCheckOutDate()));
        preparedStatement.setInt(3, reservation.getRoom().getRoomNumber());
        preparedStatement.setInt(4, reservation.getCustomer().getId());
        preparedStatement.setInt(5, reservation.getReservationId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteReservation(int reservationId) throws SQLException {
        Connection connection = SingletonConnection.getConnection();
        String query = "DELETE FROM reservation WHERE reservationId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        preparedStatement.executeUpdate();
    }

    @Override
    public Reservation getReservationById(int reservationId) throws SQLException {
        Reservation reservation = new Reservation();
        Connection connection = SingletonConnection.getConnection();
        String query = "SELECT * FROM reservation WHERE reservationId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            reservation.setReservationId(resultSet.getInt("reservationId"));
            reservation.setCheckInDate(resultSet.getDate("checkInDate").toLocalDate());
            reservation.setCheckOutDate(resultSet.getDate("checkOutDate").toLocalDate());

            Customer customer = new Customer();
            reservation.setCustomer(customer.getCustomerById(resultSet.getInt("customerId")));

            Room room = new Room();
            reservation.setRoom(room.getRoom(resultSet.getInt("roomId")));
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        String query = "SELECT * FROM reservation";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("Fetching reservation with ID: " + resultSet.getInt("reservationId"));
            Reservation reservation = new Reservation();
            reservation.setReservationId(resultSet.getInt("reservationId"));

            Customer customer = new Customer();
            reservation.setCustomer(customer.getCustomerById(resultSet.getInt("customerId")));

            Room room = new Room();
            reservation.setRoom(room.getRoom(resultSet.getInt("roomId")));

            reservation.setCheckInDate(resultSet.getDate("checkInDate").toLocalDate());
            reservation.setCheckOutDate(resultSet.getDate("checkOutDate").toLocalDate());
            reservations.add(reservation);
        }
        System.out.println("Total reservations fetched: " + reservations.size());
        return reservations;
    }
}
