/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

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
                       SELECT * FROM CUSTOMER INNER JOIN ACCOUNT ON CUSTOMER.PHONE = ACCOUNT.PHONE INNER JOIN CUSTOMER_LANDLORD_PROFILE
        ON CUSTOMER_LANDLORD_PROFILE.CCCD = CUSTOMER.CCCD WHERE ACCOUNT.PHONE=?""";
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
                customer.setBirthday(result.getString("BIRTHDAY"));
                customer.setContractId(result.getInt("CONTRACTID"));
                customer.setBankAccount(result.getString("BANKACCOUNT"));
                customer.setRelativeName(result.getString("RELATIVENAME"));
                customer.setRelativeNumber(result.getString("RELATIVEPHONE"));
                customer.setRoomIdMatch(result.getInt("ROOMIDMATCH"));
                customer.setEmail(result.getString("EMAIL"));
                customer.setImg(result.getString("PATH"));
                return customer;
             }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
