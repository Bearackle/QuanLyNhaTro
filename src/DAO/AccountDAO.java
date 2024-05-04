/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import model.User;
/**
 *
 * @author Admin
 */
public class AccountDAO {
    private Connection connection;
    public AccountDAO()
    {
       DataBaseConnection.CreateConnection();
       connection = DataBaseConnection.getConnection();
    }
    public User Authentication(User user)
    { 
        try{
        String query = "SELECT * FROM ACCOUNT WHERE PHONE=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,user.getPhone());
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next())
            {
                User authenticateUser = new User();
                authenticateUser.setPhone(resultSet.getString("PHONE"));
                authenticateUser.setName(resultSet.getString("NAME"));
                authenticateUser.setPassword("PASSWORD");
                authenticateUser.setEmail(resultSet.getString("EMAIL"));
                authenticateUser.setRole(resultSet.getString("ROLE"));
                if (resultSet.getString("PASSWORD").equals(user.getPassword()))
                    return authenticateUser;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void CreateUser(User user)
    {
        String query = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?)";
        try
        {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getName());
            ps.setString(3, user.getRole());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.execute();
        } catch (SQLException e){
             e.printStackTrace();
         }
    }
    public User getUserByPhoneNumber(String Phone)
    {
        User user = new User();
        String query = "SELECT PHONE, NAME, EMAIL FROM ACCOUNT WHERE PHONE=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                user.setPhone(rs.getString(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setImg("icon/PROFILE_IMAGE/PR0E4.jpg");
                return user;
            }         
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isCustomer (String phone)
    {
        String query = "SELECT CCCD FROM CUSTOMER WHERE PHONE=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
