package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IGestionRoom;
import dao.SingletonConnection;

public class Room implements IGestionRoom {
    private int roomNumber;
    private String roomType; // e.g., Single, Double, Suite
    private boolean isAvailable;
    
    public Room() {
    	
    }

    public Room(int roomNumber, String roomType, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public List<Room> getRooms() throws SQLException{
        // TODO Auto-generated method stub
    	List<Room> rooms = new ArrayList<>();
        Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM room");
        ResultSet rs = st.executeQuery();
        Room room = new Room();
        while (rs.next()) {
        	
        	room.setRoomNumber(rs.getInt(1));
        	room.setRoomType(rs.getString(2));
        	room.setAvailable(rs.getBoolean(3));
            rooms.add(room);
        }

        return rooms;
    }

    @Override
    public void addRoom(Room room) throws SQLException{
        // TODO Auto-generated method stub
    	Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("INSERT INTO room (roomNumber, roomType, isAvailable) VALUES (?,?,?)");
        st.setInt(1, getRoomNumber());
        st.setString(2, getRoomType());
        st.setBoolean(3, isAvailable());
        st.executeUpdate();
    }

    @Override
    public void removeRoom(Room room) throws SQLException{
        // TODO Auto-generated method stub
    	Connection cnx = SingletonConnection.getConnection();
        PreparedStatement st = cnx.prepareStatement("DELETE FROM room WHERE roomNumber=?");
        st.setInt(1, room.getRoomNumber());
        st.executeUpdate();
    }

    @Override
    public void updateRoom(Room room) throws SQLException{
        // TODO Auto-generated method stub
    	 Connection cnx = SingletonConnection.getConnection();
         PreparedStatement st = cnx.prepareStatement("UPDATE room SET roomType=?, isAvailable=?");
         st.setString(1, room.getRoomType());
         st.setBoolean(2, room.isAvailable());
         st.executeUpdate();
    }

    @Override
    public Room getRoom(int roomNumber) throws SQLException{
        // TODO Auto-generated method stub
    	Connection cnx = SingletonConnection.getConnection();
        Room room = new Room();
        PreparedStatement st = cnx.prepareStatement("SELECT * FROM room WHERE roomNumber=?");
        st.setInt(1, roomNumber);
        var rs = st.executeQuery();
        if (rs.next()) {
        	room.setRoomNumber(rs.getInt(1));
        	room.setRoomType(rs.getString(2));
        	room.setAvailable(rs.getBoolean(3));
        }

        return room;
    }

	@Override
	public List<Room> getRoomsByType(String type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getRoomsByAvailability(Boolean availability) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
