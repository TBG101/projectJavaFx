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

    public Reservation(int reservationId, Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getReservationId() {
        return reservationId;
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

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

	@Override
	public void addReservation(Reservation reservation) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = SingletonConnection.getConnection();
		String query = "INSERT INTO reservation (reservationId, checkInDate, checkOutDate, roomId, customerId) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, getReservationId());
		preparedStatement.setDate(2, java.sql.Date.valueOf(getCheckInDate()));
		preparedStatement.setDate(3, java.sql.Date.valueOf(getCheckOutDate()));
		preparedStatement.setInt(4, getRoom().getRoomNumber());
		preparedStatement.setInt(5, getCustomer().getId());
		
		preparedStatement.executeUpdate();
	}

	@Override
	public void updateReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = SingletonConnection.getConnection();
        String query = "UPDATE reservation SET reservationId = ?, checkInDate = ?, checkOutDate = ?, roomId = ? WHERE reservationId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, getReservationId());
        preparedStatement.setDate(2, java.sql.Date.valueOf(getCheckInDate()));
        preparedStatement.setDate(3, java.sql.Date.valueOf(getCheckOutDate()));
        preparedStatement.setInt(4, getRoom().getRoomNumber());
        preparedStatement.executeUpdate();
	    
		
	}
	

	@Override
	public void deleteReservation(int reservationId) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = SingletonConnection.getConnection();
        String query = "DELETE FROM reservation WHERE reservationId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        preparedStatement.executeUpdate();
    
	}

	@Override
	public Reservation getReservationById(int reservationId) throws SQLException{
		Reservation reservation = null;
        Connection connection = SingletonConnection.getConnection();
        String query = "SELECT * FROM reservation WHERE reservation = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, reservationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            reservation.setReservationId(resultSet.getInt(1));
            reservation.setCheckInDate(resultSet.getDate(4).toLocalDate());
            reservation.setCheckOutDate(resultSet.getDate(5).toLocalDate());
            
            // Get Customer By Id
            Customer customer = null;
            reservation.setCustomer(customer.getCustomerById(resultSet.getInt(2)));;
            
            // Get Room By Id
            Room room = null;
            reservation.setRoom(room.getRoom(resultSet.getInt(3)));
            

            
        }

        return reservation;
	}

	@Override
	public List<Reservation> getAllReservations() throws SQLException{
		// TODO Auto-generated method stub
		List<Reservation> reservations = new ArrayList<>();
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM reservations");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
        	Reservation reservation = null;
        	reservation.setReservationId(rs.getInt(1));
        	 // Get Customer By Id
            Customer customer = null;
            reservation.setCustomer(customer.getCustomerById(rs.getInt(2)));;
            
            // Get Room By Id
            Room room = null;
            reservation.setRoom(room.getRoom(rs.getInt(3)));
            reservation.setCheckInDate(rs.getDate(4).toLocalDate());
            reservation.setCheckOutDate(rs.getDate(5).toLocalDate());
            reservations.add(reservation);
        }

        return reservations;
	}
}
