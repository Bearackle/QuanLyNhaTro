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
    public RoomDAO()
    {
        connection = DataBaseConnection.getConnection();
    }
    public List<Room> getAllRoom(String status)
    {
        List<Room> allRoom = new ArrayList<>();
        String query = "SELECT * FROM ROOM WHERE STATUS=?";
        String query2 = "SELECT PATH FROM ROOMIMAGE WHERE ROOMID=? AND ROWNUM=1";
        try 
        {
            PreparedStatement ps = connection.prepareStatement(query);
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps.setString(1, status);
            ResultSet result = ps.executeQuery();
            while (result.next())
            {
                Room room = new Room();
                room.setID(result.getInt("ROOMID"));
                room.setName(result.getString("NAME"));
                room.setPrices(result.getInt("PRICE"));
                room.setArea(result.getFloat("AREA"));
                room.setCategoryId(result.getInt("CATEGORYID"));
                room.setIsAllowMatch(result.getString("ISALLOWMATCH"));
                room.setDescription(result.getString("DECRIPTION"));
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
                room.setIsAllowMatch(result.getString("ISALLOWMATCH"));
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
    public boolean getRoomIsAllowMatch(int ID)
    {
         String query  = "SELECT ISALLOWMATCH FROM ROOM WHERE ROOMID=?";
         try {
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, ID);
             ResultSet rs = ps.executeQuery();
             if (rs.next()){
                 return "CÓ".equals(rs.getString(1));
             }
         }catch (SQLException e)
         {
             e.printStackTrace();
         }
         return false;
    }
    public int[] getDataRoom()
    {
        String query1 = "SELECT COUNT(*) AS CNT FROM ROOM WHERE STATUS=?";
        String query2 = "SELECT COUNT(*) AS CNTMATCH FROM ROOM WHERE ISALLOWMATCH=?";
        String query3 = "SELECT COUNT(*) AS CNTSHORT FROM ROOM WHERE CATEGORYID=600";
        int[] arr = new int[3];
        try
            {
                PreparedStatement ps1 = connection.prepareStatement(query1);
                PreparedStatement ps2 = connection.prepareStatement(query2);
                PreparedStatement ps3 = connection.prepareStatement(query3);
                ps1.setString(1, "TRỐNG");
                ps2.setString(1, "CÓ");
                ResultSet resultSet1 = ps1.executeQuery();
                ResultSet resultSet2 = ps2.executeQuery();
                ResultSet resultSet3 = ps3.executeQuery();
                if(resultSet1.next())
                {
                    arr[0]=resultSet1.getInt("CNT");
                }
                if (resultSet2.next())
                {
                    arr[1] = resultSet2.getInt("CNTMATCH");
                }
                if (resultSet3.next()){
                    arr[2] = resultSet3.getInt(1);
                }
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
                str = String.join(" ",str,resultSet.getString("PATH"));
            }
            str = str.trim();
           return str.split(" ");
        } catch (SQLException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
    public boolean UpdatePolicy (Long ID,String state){
        String query = "UPDATE ROOM SET ISALLOWMATCH=? WHERE ROOMID=(SELECT ROOM_ID FROM CONTRACT WHERE CUSTOMER_ID=?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, state);
            ps.setLong(2, ID);
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
