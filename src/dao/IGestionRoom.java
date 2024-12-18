package dao;

import java.util.List;

import entities.Room;

public interface IGestionRoom {
    int getRoomNumber();

    void setRoomNumber(int roomNumber);

    String getRoomType();

    void setRoomType(String roomType);

    boolean isAvailable();

    void setAvailable(boolean available);

    List<Room> getRooms();

    void addRoom(Room room);

    void removeRoom(Room room);

    void updateRoom(Room room);

    Room getRoom(int roomNumber);


}
