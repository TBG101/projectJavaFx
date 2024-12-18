package dao;

import entities.Payment;

import java.sql.SQLException;
import java.util.List;

public interface IGestionPayment {
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(int paymentId);
    Payment getPaymentById(int paymentId) throws SQLException;
    List<Payment> getAllPayments();
}
