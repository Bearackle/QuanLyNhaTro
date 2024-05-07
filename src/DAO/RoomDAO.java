/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Location;
import model.Room;
import model.RoomService;

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
    public Room getDataRoomWithCustomerID(Long ID){
        String query = "SELECT ROOM.ROOMID, CATEGORYID ROOM_ FROM CUSTOMER INNER JOIN CONTRACT ON CUSTOMER.CCCD=CONTRACT.CUSTOMER_ID INNER JOIN ROOM ON ROOM.ROOMID=CONTRACT.ROOM_ID WHERE CUSTOMER.CCCD=? AND CONTRACT.STATUS='ĐÃ DUYỆT'";
        try {
            Room falseRoom = new Room();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                falseRoom.setID(rs.getInt(1));
                falseRoom.setCategoryId(rs.getInt(2)); 
            }
            return falseRoom;
        } catch(SQLException e) {
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
    public ArrayList<RoomService> GetAllRoomService(int RoomID){
        String query = "SELECT * FROM ROOMSERVICE WHERE ROOM_ID=?";
        ArrayList<RoomService> allServices = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, RoomID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RoomService roomService = new RoomService();
                roomService.setID(rs.getInt(1));
                roomService.setRoomID(rs.getInt(2));
                roomService.setDescription(rs.getString(3));
                roomService.setPrice(rs.getInt(4));
                roomService.setCreateDate(rs.getDate(5));
                allServices.add(roomService);
            }
            return allServices;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean RequestRoomService(RoomService roomService){
        String query = "INSERT INTO ROOMSERVICE(ROOM_ID,DESCRIPTION,PRICE,CREATED_DATE) VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,roomService.getRoomID());
            ps.setString(2,roomService.getDescription());
            ps.setInt(3,roomService.getPrice());
            ps.setDate(4,new java.sql.Date(roomService.getCreateDate().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean isAllowmatch(int ID){
         String query = "SELECT ISALLOWMATCH FROM ROOM WHERE ROOMID=?";
         try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString(1).equalsIgnoreCase("Có");
            }
         } catch (SQLException e){
             e.printStackTrace();
         }
         return false;
    }
}
