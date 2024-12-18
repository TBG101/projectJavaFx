package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.IGestionPayment;
import dao.SingletonConnection;

public class Payment implements IGestionPayment {
    private int paymentId;
    private Reservation reservation;
    private double amountPaid;
    private LocalDate paymentDate;
    private String paymentMethod;
    

    public Payment(int paymentId, Reservation reservation, double amountPaid, LocalDate paymentDate,
            String paymentMethod) {
        this.paymentId = paymentId;
        this.reservation = reservation;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return paymentId;
    }
    

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void addPayment(Payment payment) {
        try (Connection connection = SingletonConnection.getConnection()) {
            String query = "INSERT INTO payments (paymentId, reservationId, amountPaid, paymentDate, paymentMethod) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, payment.getPaymentId());
            preparedStatement.setInt(2, payment.getReservation().getReservationId());
            preparedStatement.setDouble(3, payment.getAmountPaid());
            preparedStatement.setDate(4, java.sql.Date.valueOf(payment.getPaymentDate()));
            preparedStatement.setString(5, payment.getPaymentMethod());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        try (Connection connection = SingletonConnection.getConnection()) {
            String query = "UPDATE payments SET reservationId = ?, amountPaid = ?, paymentDate = ?, paymentMethod = ? WHERE paymentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, payment.getReservation().getReservationId());
            preparedStatement.setDouble(2, payment.getAmountPaid());
            preparedStatement.setDate(3, java.sql.Date.valueOf(payment.getPaymentDate()));
            preparedStatement.setString(4, payment.getPaymentMethod());
            preparedStatement.setInt(5, payment.getPaymentId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        try (Connection connection = SingletonConnection.getConnection()) {
            String query = "DELETE FROM payments WHERE paymentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) throws SQLException{
        Payment payment = null;
        Connection connection = SingletonConnection.getConnection();
        String query = "SELECT * FROM payments WHERE paymentId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, paymentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            payment.setPaymentId(resultSet.getInt("id"));
            payment.setAmountPaid(resultSet.getInt("amountPaid"));
            payment.setPaymentMethod(resultSet.getString("paymentMethod"));
            payment.setPaymentDate(resultSet.getDate("paymentDate").toLocalDate());
            Reservation reservation = new Reservation();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM reservation WHERE id=?");
            ps.setInt(1, resultSet.getInt("reservationId"));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reservation.setReservationId(rs.geInt("id"));
                reservation.setCheckInDate(rs.getString("checkInDate")).LocalDate();
                

            }

            
        }

        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (Connection connection = SingletonConnection.getConnection()) {
            String query = "SELECT * FROM payments";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Payment payment = new Payment(
                        resultSet.getInt("paymentId"),
                        new Reservation(resultSet.getInt("reservationId")), // Assuming Reservation has a constructor
                                                                            // with reservationId
                        resultSet.getDouble("amountPaid"),
                        resultSet.getDate("paymentDate").toLocalDate(),
                        resultSet.getString("paymentMethod"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void processPayment(Payment payment) {
        addPayment(payment);
    }
}
