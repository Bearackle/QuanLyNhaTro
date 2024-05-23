/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;
import model.Report;
import model.ResidentDetail;

/**
 *
 * @author Admin
 */
public class CustomerDAO {
    private Connection connection;
    public CustomerDAO()
    {
        connection = DataBaseConnection.getConnection();
    }
    public Customer getCustomer(String sdt)
    {
        String query = """
                       SELECT * FROM CUSTOMER INNER JOIN ACCOUNT ON CUSTOMER.PHONE = ACCOUNT.PHONE WHERE ACCOUNT.PHONE=?""";
        try 
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,sdt);
            ResultSet result = ps.executeQuery();
            if (result.next())
            {
                Customer customer = new Customer();
                customer.setCCCD(result.getLong("CCCD"));
                customer.setName(result.getString("NAME"));
                customer.setPhone(result.getString("PHONE"));
                customer.setGender(result.getString("GENDER"));
                customer.setBirthday(result.getDate("BIRTHDAY").toLocalDate());
                customer.setBankAccount(result.getString("BANKACCOUNT"));
                customer.setRelativeName(result.getString("RELATIVENAME"));
                customer.setRelativeNumber(result.getString("RELATIVEPHONE"));
                customer.setRoomIdMatch(result.getInt("ROOMIDMATCH"));
                customer.setEmail(result.getString("EMAIL"));
                customer.setImg("icon/PROFILE_IMAGE/PR0E4.jpg");
                return customer;
             }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void CreeateNewCustomer(Customer customer){
         String query = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?,?,?)";
         String query2= "INSERT INTO RESIDENT_REGISTRATION VALUES(?,?,?,?,?)";
         try {
             PreparedStatement ps = connection.prepareStatement(query);
             PreparedStatement ps2 = connection.prepareStatement(query2);
             ps.setLong(1,customer.getCCCD());
             ps.setString(2, customer.getName());
             ps.setString(3, customer.getPhone());
             ps.setString(4, customer.getGender());
             ps.setDate(5,java.sql.Date.valueOf(customer.getBirthday()));
             ps.setString(6, customer.getBankAccount());
             ps.setString(7, customer.getRelativeName());
             ps.setString(8, customer.getRelativeNumber());
             ps.setNull(9,Types.INTEGER);
             ps2.setLong(1, customer.getCCCD());
             ps2.setNull(2, Types.VARCHAR);
             ps2.setNull(3, Types.VARCHAR);
             ps2.setNull(4, Types.VARCHAR);
             ps2.setNull(5,Types.VARCHAR);
             ps.executeUpdate();
             ps2.executeUpdate();
         } catch (SQLException e)
         {
             e.printStackTrace();
         }
    }
    public ResidentDetail getResidentDetail(Long id){
        String query = "SELECT permanent_resident,temporary_accommodation, current_resident, current_work FROM RESIDENT_REGISTRATION WHERE CCCD=?";
        ResidentDetail rsd = new ResidentDetail();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
               rsd.setCCCD(id);
               rsd.setPermanent_resident(rs.getString(1));
               rsd.setTemporary_accommodation(rs.getString(2));
                rsd.setCurrent_resident(rs.getString(3));
               rsd.setCurrent_work(rs.getString(4));
               return rsd;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean UpdateResident(ResidentDetail resident){
        String query = "UPDATE RESIDENT_REGISTRATION SET permanent_resident=?, temporary_accommodation=?,current_resident=?,  current_work=? WHERE CCCD=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, resident.getPermanent_resident());
            ps.setString(2, resident.getTemporary_accommodation());
            ps.setString(3, resident.getCurrent_resident());
            ps.setString(4, resident.getCurrent_work());
            ps.setLong(5, resident.getCCCD());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean UpdateRoomIDMatch(String phone,int RoomID){
        String query = "UPDATE CUSTOMER SET ROOMIDMATCH=? WHERE PHONE=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, RoomID);
            ps.setString(2, phone);
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Customer> getAllRoommate(int RoomID){
        String query = "SELECT NAME,PHONE FROM CUSTOMER WHERE ROOMIDMATCH=?";
        ArrayList <Customer> Roommates = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, RoomID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customer roommate = new Customer();
                roommate.setName(rs.getString(1));
                roommate.setPhone(rs.getString(2));
                Roommates.add(roommate);
            }
            return Roommates;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Report> getAllReport(){
         String query = "SELECT * FROM REPORTCUSTOMER";
         ArrayList<Report> list = new ArrayList<>();
         try{
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Report rp = new Report();
                 rp.setRoomid(rs.getInt(1));
                 rp.setCCCDR(rs.getLong(2));
                 rp.setContent(rs.getString(3));
                 rp.setSolve("YES".equals(rs.getString(4)));
                list.add(rp);
             }
             return list;
             } catch (SQLException e){
                    e.printStackTrace();
           }
         return null;
    }
    public boolean CreateNewReport(Report report){
        String query = "INSERT INTO REPORTCUSTOMER VALUES(?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, report.getRoomidReport());
            ps.setLong(2, report.getCCCDR());
            ps.setString(3, report.getContent());
            ps.setString(4, report.IsSolve()? "YES" : "NO");
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean UpdateSolveReport(Report report){
        String query = "UPDATE REPORTCUSTOMER SET ISSOLVE='YES' WHERE ROOMID=? AND FROM_CUSTOMER=? AND REPORTCONTENT=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, report.getRoomidReport());
            ps.setLong(2, report.getCCCDR());
            ps.setString(3, report.getContent());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
