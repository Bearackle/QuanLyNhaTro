/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.lang.reflect.AccessFlag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Location;
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
        String query2 = "SELECT * FROM ROOMIMAGE WHERE ROOMID=?";
        try 
        {
            PreparedStatement ps = connection.prepareStatement(query);
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                Room room = new Room();
                room.setID(result.getInt("ROOMID"));
                room.setName(result.getString("NAME"));
                room.setPrices(result.getInt("PRICE"));
                room.setArea(result.getFloat("AREA"));
                //
                
                String[] dblocation = result.getString("LOCATION").split(",");
                room.setLocation(new Location(dblocation[0],dblocation[1],dblocation[2],dblocation[3]));
                //
                ps2.setInt(1,room.getID());
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
    public Room getRoomInfoWithId(int RoomId)
    {
        try {
        String query = "SELECT * FROM ROOM WHRERE ROOMID=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, RoomId);
        ResultSet result = ps.executeQuery();
        if (result.next())
        {
            Room room = new Room();
               room.setID(result.getInt("ID"));
                room.setName(result.getString("NAME"));
                room.setPrices(result.getInt("PRICES"));
                room.setArea(result.getFloat("AREA"));
                //

                String[] dblocation = result.getString("LOCATION").split(",");
                room.setLocation(new Location(dblocation[0],dblocation[1],dblocation[2],dblocation[3]));
            return room;
        }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
