package interfeace;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entities.Customer;
import entities.Payment;
import entities.Reservation;
import entities.Room;

public class Test {
    public static void main(String[] args) {
        try {
            Reservation reservation = new Reservation();
            List<Reservation> reservations = reservation.getAllReservations();
            System.out.println("Number of reservations loaded: " + reservations.size());
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Customer customer = new Customer();
            List<Customer> customers = customer.getAllCustomers();
            System.out.println("Number of customers loaded: " + customers.size());
            for (Customer c : customers) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }}

}
