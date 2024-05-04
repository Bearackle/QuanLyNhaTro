/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import model.Customer;
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
                customer.setBirthday(result.getDate("BIRTHDAY"));
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
         String query = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?,?,?,?)";
         try {
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setLong(1,customer.getCCCD());
             ps.setString(2, customer.getName());
             ps.setString(3, customer.getPhone());
             ps.setString(4, customer.getGender());
             ps.setDate(5,new java.sql.Date(customer.getBirthday().getTime()));
             ps.setNull(6,Types.INTEGER);
             ps.setString(7, customer.getBankAccount());
             ps.setString(8, customer.getRelativeName());
             ps.setString(9, customer.getRelativeNumber());
             ps.setNull(10, Types.INTEGER);
             ps.executeUpdate();
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
}
