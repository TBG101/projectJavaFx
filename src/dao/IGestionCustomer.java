package dao;

import entities.Customer;

import java.sql.SQLException;
import java.util.List;

public interface IGestionCustomer {
    void addCustomer(Customer customer) throws SQLException;

    void updateCustomer(Customer customer) throws SQLException;

    void deleteCustomer(int customerId) throws SQLException;

    Customer getCustomerById(int customerId) throws SQLException;

    List<Customer> getAllCustomers() throws SQLException;

    List<Customer> getCustomersByName(String customerName) throws SQLException;
}
