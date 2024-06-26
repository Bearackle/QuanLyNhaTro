/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.security.interfaces.RSAKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Location;
import model.Review;
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
        String query = "SELECT * FROM ROOM WHERE STATUS=? OR (STATUS!='XÓA'AND ISALLOWMATCH='CÓ')";
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
                room.setDescription(result.getString("DESCRIPTION"));
                //
                String[] dblocation = result.getString("LOCATION").split(",");
                room.setLocation(new Location(dblocation[0],dblocation[1],dblocation[2],dblocation[3]));
                //
                room.setStatus(result.getString("STATUS"));
                room.setLandLordContractID(result.getInt("LANDLORDCONTRACTID"));
                room.setVote(result.getInt("VOTE"));
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
     public ArrayList<Room> getAllRoomNoMatter()
    {
        ArrayList<Room> allRoom = new ArrayList<>();
        String query = "SELECT * FROM ROOM";
        try 
        {
            PreparedStatement ps = connection.prepareStatement(query);
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
                room.setDescription(result.getString("DESCRIPTION"));
                room.setStatus(result.getString("STATUS"));
                room.setLandLordContractID(result.getInt("LANDLORDCONTRACTID"));
                room.setVote(result.getInt("VOTE"));
                String[] dblocation = result.getString("LOCATION").split(",");
                room.setLocation(new Location(dblocation[0],dblocation[1],dblocation[2],dblocation[3]));
                //
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
                room.setVote(result.getInt("VOTE"));
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
        String query3 = "SELECT COUNT(*) AS CNTSHORT FROM ROOM WHERE CATEGORYID=600 AND STATUS='TRỐNG'";
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
        String query = "UPDATE ROOM SET ISALLOWMATCH=? WHERE ROOMID=(SELECT ROOM_ID FROM CONTRACT WHERE CUSTOMER_ID=? AND CONTRACT.STATUS='ĐÃ DUYỆT')";
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
                roomService.setCreateDate(rs.getDate(5).toLocalDate());
                roomService.setStatus(rs.getString(6));
                allServices.add(roomService);
            }
            return allServices;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<RoomService> GetAllRoomServiceNoMatter(){
        String query = "SELECT * FROM ROOMSERVICE";
        ArrayList<RoomService> allServices = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                RoomService roomService = new RoomService();
                roomService.setID(rs.getInt(1));
                roomService.setRoomID(rs.getInt(2));
                roomService.setDescription(rs.getString(3));
                roomService.setPrice(rs.getInt(4));
                roomService.setCreateDate(rs.getDate(5).toLocalDate());
                roomService.setStatus(rs.getString(6));
                allServices.add(roomService);
            }
            return allServices;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean RequestRoomService(RoomService roomService){
        String query = "INSERT INTO ROOMSERVICE(ROOM_ID,DESCRIPTION,PRICE,CREATED_DATE,STATUS) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,roomService.getRoomID());
            ps.setString(2,roomService.getDescription());
            ps.setInt(3,roomService.getPrice());
            ps.setDate(4, java.sql.Date.valueOf(roomService.getCreateDate()));
            ps.setString(5, "CHƯA THỰC HIỆN");
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
    public boolean InsertImage(int RoomID,String path){
        String query = "INSERT INTO ROOMIMAGE VALUES (?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,RoomID);
            ps.setString(2,path);
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateStatusRoom(int ID, String status){
        String query = "UPDATE ROOM SET STATUS=? WHERE ROOMID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean UpdateStatusRoomThroughLandlordcontractid(int ID,String status){
        String query = "UPDATE ROOM SET STATUS=? WHERE LANDLORDCONTRACTID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateDataRoom(Room room){
        String query = "UPDATE ROOM SET NAME=?,AREA=?,LOCATION=?,PRICE=?,DESCRIPTION=?,STATUS=?,CATEGORYID=?,LANDLORDCONTRACTID=?,VOTE=?,ISALLOWMATCH=? WHERE ROOMID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, room.getName());
            ps.setDouble(2, room.getArea());
            ps.setString(3 ,room.getLocation().toString());
            ps.setInt(4,room.getPrices());
            ps.setString(5, room.getDescription());
            ps.setString(6, room.getStatus());
            ps.setInt(7, room.getCategoryId());
            ps.setInt(8, room.getLandLordContractID());
            ps.setInt(9, room.getVote());
            ps.setString(10, room.isIsAllowMatch());
            ps.setInt(11, room.getID());
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateStatusRoomService(RoomService roomService){
        String query = "UPDATE ROOMSERVICE SET ROOM_ID=?,DESCRIPTION=?,PRICE=?,CREATED_DATE=?, STATUS=? WHERE SERVICE_ID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, roomService.getRoomID());
            ps.setString(2, roomService.getDescription());
            ps.setInt(3, roomService.getPrice());
            ps.setDate(4, java.sql.Date.valueOf(roomService.getCreateDate()));
            ps.setString(5, roomService.getStatus());
            ps.setInt(6, roomService.getID());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean InsertReview(Review review){
        String query ="INSERT INTO REVIEWROOM VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            CallableStatement st = connection.prepareCall("{call CNTROOMVOTE(?)}");
            ps.setInt(1, review.getRoomid());
            ps.setLong(2, review.getCustomerid());
            ps.setString(3, review.getContent());
            ps.setInt(4, review.getVote());
            ps.executeUpdate();
            st.setInt(1, review.getRoomid());
            st.execute();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Review> getAllReviewRoomByRoomId(int Roomid){
        String query ="SELECT ROOMID,CUSTOMERID,NAME,REVIEW,VOTE FROM REVIEWROOM INNER JOIN CUSTOMER ON CUSTOMERID=CCCD WHERE ROOMID=?";
        ArrayList<Review> list = new ArrayList<>();
        try{
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, Roomid);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Review review = new Review();
                 review.setRoomid(rs.getInt(1));
                 review.setCustomerid(rs.getLong(2));
                 review.setCustmerName(rs.getString(3));
                 review.setContent(rs.getString(4));
                 review.setVote(rs.getInt(5));
                 list.add(review);
             }
             return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
