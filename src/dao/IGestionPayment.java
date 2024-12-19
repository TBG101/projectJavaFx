package dao;

import entities.Payment;

import java.sql.SQLException;
import java.util.List;

public interface IGestionPayment {
    void addPayment(Payment payment) throws SQLException;
    void updatePayment(Payment payment) throws SQLException;
    void deletePayment(int paymentId) throws SQLException;
    Payment getPaymentById(int paymentId) throws SQLException;
    List<Payment> getAllPayments() throws SQLException;
}
