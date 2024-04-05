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
    public User Authentication(User user)
    {
        connection = DataBaseConnection.getConnection();
        try{
        String query = "SELECT * FROM SYS.ACCOUNT WHERE PHONE=?";
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
}
