import java.sql.Connection;
import java.util.List;

import dao.SingletonConnection;
import entities.Customer;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Customer customer1 = new Customer(1, "John Doe", "1234567890", "john@example.com");
        Customer customer2 = new Customer(2, "Jane Smith", "0987654321", "jane@example.com");

        // Add customers
        customer1.addCustomer(customer1);
        customer1.addCustomer(customer2);

        // Update customer
        customer1.setName("John Updated");
        customer1.updateCustomer(customer1);

        // Get customer by ID
        Customer retrievedCustomer = customer1.getCustomerById(1);
        System.out.println("Retrieved Customer: " + retrievedCustomer.getName());

        // Get all customers
        List<Customer> allCustomers = customer1.getAllCustomers();
        System.out.println("All Customers: ");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getName());
        }

        // Get customers by name
        List<Customer> customersByName = customer1.getCustomersByName("Jane");
        System.out.println("Customers with name 'Jane': ");
        for (Customer customer : customersByName) {
            System.out.println(customer.getName());
        }

        // Delete customer
        customer1.deleteCustomer(2);
    }
}
