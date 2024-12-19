package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Room;

public interface IGestionRoom {


    List<Room> getRooms() throws SQLException;
    
    List<Room> getRoomsByType(String type) throws SQLException;
    
    List<Room> getRoomsByAvailability(Boolean availability) throws SQLException;

    void addRoom(Room room) throws SQLException;

    void removeRoom(Room room) throws SQLException;

    void updateRoom(Room room) throws SQLException;

    Room getRoom(int roomNumber) throws SQLException;


}
