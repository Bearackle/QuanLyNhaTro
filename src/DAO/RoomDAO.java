/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
        String query2 = "SELECT PATH FROM ROOMIMAGE WHERE ROOMID=? AND ROWNUM=1";
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
                room.setCategoryId(result.getInt("CATEGORYID"));
                //
                String[] dblocation = result.getString("LOCATION").split(",");
                room.setLocation(new Location(dblocation[0],dblocation[1],dblocation[2],dblocation[3]));
                //
                ps2.setInt(1,room.getID());
                ResultSet thisRoomIcon = ps2.executeQuery();
                room.setIconList(thisRoomIcon.next() ? thisRoomIcon.getString("PATH") : "NOT FOUND");
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
    public int[] getDataRoom()
    {
        String query = "SELECT COUNT(*) AS CNT FROM ROOM WHERE STATUS=?";
        String query1 = "SELECT COUNT(*) AS CNTMATCH FROM ROOM WHERE ISALLOWMATCH=?";
        int[] arr = new int[3];
        try
            {
                PreparedStatement ps1 = connection.prepareStatement(query);
                PreparedStatement ps2 = connection.prepareStatement(query1);
                ps1.setString(1, "TRỐNG");
                ps2.setString(1, "CÓ");
                ResultSet resultSet = ps1.executeQuery();
                ResultSet resultSet1 = ps2.executeQuery();
                if(resultSet.next())
                {
                    arr[0]=resultSet.getInt("CNT");
                }
                if (resultSet1.next())
                {
                    arr[1] = resultSet1.getInt("CNTMATCH");
                }
                arr[2] = 0;
                return arr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String[] getRoomImages(int RoomId)
    {
        try {
            String query = "SELECT PATH FROM ROOMIMAGE WHERE ROOMID=?";
            String str = new String();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, RoomId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                str = String.join(" ",resultSet.getString("PATH"));
            }
           return str.split(" ");
        } catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}
