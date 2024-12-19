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
        	System.out.println("\nTesting Room Functions:");
            Room room1 = new Room(101, "Single", true);

            Room retrievedRoom = room1.getRoom(101);
            System.out.println("Retrieved Room: " + retrievedRoom.getRoomType());

            retrievedRoom.setRoomType("Double");
            room1.updateRoom(retrievedRoom);
            System.out.println("Updated Room: " + room1.getRoom(101).getRoomType());

            List<Room> rooms = room1.getRooms();
            System.out.println("All Rooms: " + rooms.size());

            room1.removeRoom(retrievedRoom);
            System.out.println("Room Deleted Successfully");

            
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
