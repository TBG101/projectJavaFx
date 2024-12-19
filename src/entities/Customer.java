package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.IGestionCustomer;
import dao.SingletonConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Customer implements IGestionCustomer {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("INSERT INTO customers (name, phoneNumber,email) VALUES (?,?,?)");
        st.setString(1, customer.getName());
        st.setString(2, customer.getPhoneNumber());
        st.setString(3, customer.getEmail());
        st.executeUpdate();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx
                .prepareStatement("UPDATE customers SET name=?, phoneNumber=?, email=? WHERE id=?");
        st.setString(1, customer.getName());
        st.setString(2, customer.getPhoneNumber());
        st.setString(3, customer.getEmail());
        st.setInt(4, customer.getId());
        st.executeUpdate();
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("DELETE FROM customers WHERE id=?");
        st.setInt(1, customerId);
        st.executeUpdate();
    }

    @Override
    public Customer getCustomerById(int customerId) throws SQLException {
        Connection cnx = SingletonConnection.getConnection();
        Customer customer = null;
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM customers WHERE id=?");
        st.setInt(1, customerId);
        var rs = st.executeQuery();
        if (rs.next()) {
            customer = new Customer(
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"));
            customer.setId(customerId);
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM customers");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"));
            customer.setId(rs.getInt("id"));
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public List<Customer> getCustomersByName(String customerName) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM customers WHERE name LIKE ?");
        st.setString(1, "%" + customerName + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("email"));
            customer.setId(rs.getInt("id"));
            customers.add(customer);
        }

        return customers;
    }
}