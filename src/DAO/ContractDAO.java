/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Contract;
/**
 *
 * @author Admin
 */
public class ContractDAO {
    private Connection connection;
    public List<Contract> getAllContractByCustomer (Long ID)
    {
        connection = DataBaseConnection.getConnection();
        List<Contract> allContracts = new ArrayList<>();
        String query = "SELECT * FROM CONTRACT WHERE CUSTOMER_ID=?";
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, ID);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next())
            {
                Contract contract = new Contract();
                contract.setID(result.getInt("CONTRACTID"));
                contract.setCustomerCCCD(result.getLong("CUSTOMER_ID"));
                contract.setCustomerName(result.getString("CUSTOMER_NAME"));
                contract.setRoomID(result.getInt("ROOM_ID"));
                contract.setDuration(result.getInt("DURATION"));
                contract.setPrice(result.getInt("PRICE"));
                contract.setSigned_date(result.getString("SIGN_DATE"));
                contract.setStatus(result.getString("STATUS"));
                contract.setElecticPrice(result.getInt("ELECTRICPRICE"));
                contract.setWaterPrice(result.getInt("WATERPRICE"));
                contract.setDeposit(result.getInt("DEPOSIT"));
                contract.setEnterDate(result.getString("ENTER_DATE"));
                contract.setCancelDate(result.getString("CANCEL_DATE"));
  
            allContracts.add(contract);
            }
            return allContracts;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
