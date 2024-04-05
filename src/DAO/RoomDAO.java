/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;

/**
 *
 * @author Admin
 */
public class RoomDAO {
    private Connection connection;
    public List<Room> getAllRoom()
    {
        connection = DataBaseConnection.getConnection();
        List<Room> allRoom = new ArrayList<>();
        String query = "SELECT * FROM ROOM";
        String query2 = "SELECT * FROM ROOMIMAGE WHERE ID=?";
        try 
        {
            PreparedStatement ps = connection.prepareStatement(query);
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                Room room = new Room();
                room.setId(result.getInt("ID"));
                room.setName(result.getString("NAME"));
                room.setPrices(result.getInt("PRICES"));
                room.setArea(result.getFloat("AREA"));
                //
                var tempRoom = new Room();
                var newLocation = tempRoom.new Location();
                String[] dblocation = result.getString("LOCATION").split(",");
                newLocation.setStreet(dblocation[0]);
                newLocation.setWard(dblocation[1]);
                newLocation.setDistrict(dblocation[2]);
                newLocation.setCty(dblocation[3]);
                room.setLocation(newLocation);
                //
                ps2.setInt(1,room.getId());
                String iconList ="";
                ResultSet allOfthisRoomIcon = ps2.executeQuery();
                while(allOfthisRoomIcon.next())
                {
                    iconList = allOfthisRoomIcon.getString("PATH");
                }
                room.setIconList(iconList);
                allRoom.add(room);
            }
          return allRoom;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
