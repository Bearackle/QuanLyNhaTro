/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import model.Contract;
import model.ContractDetail;
import model.Location;
/**
 *
 * @author Admin
 */
public class ContractDAO {
    private Connection connection;
    public ContractDAO()
    {
        connection = DataBaseConnection.getConnection();
    }
    public List<Contract> getAllContractByCustomer (Long ID)
    {
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
                contract.setSigned_date(result.getDate("SIGN_DATE"));
                contract.setStatus(result.getString("STATUS"));
                contract.setElecticPrice(result.getInt("ELECTRICPRICE"));
                contract.setWaterPrice(result.getInt("WATERPRICE"));
                contract.setDeposit(result.getInt("DEPOSIT"));
                contract.setEnterDate(result.getDate("ENTER_DATE"));
                contract.setCancelDate(result.getDate("CANCEL_DATE"));
  
            allContracts.add(contract);
            }
            return allContracts;
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public Contract getAvailableContractByCustomerID(Long ID)
    {
        String query = "SELECT * FROM CONTRACT WHERE CUSTOMER_ID=? AND STATUS='ĐÃ DUYỆT'";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, ID);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                Contract contract = new Contract();
                contract.setID(resultSet.getInt("CONTRACTID"));
                contract.setCustomerCCCD(ID);
                contract.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
                contract.setRoomID(resultSet.getInt("ROOM_ID"));
                contract.setDuration(resultSet.getInt("DURATION"));
                contract.setPrice(resultSet.getInt("PRICE"));
                contract.setSigned_date(resultSet.getDate("SIGN_DATE"));
                contract.setStatus(resultSet.getString("STATUS"));
                contract.setElecticPrice(resultSet.getInt("ELECTRICPRICE"));
                contract.setWaterPrice(resultSet.getInt("WATERPRICE"));
                contract.setEnterDate(resultSet.getDate("ENTER_DATE"));
                contract.setCancelDate(resultSet.getDate("CANCEL_DATE"));
                contract.setDeposit(resultSet.getInt("DEPOSIT"));
               return contract;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ContractDetail contractDetailCustomer(int contractID)
    {
        String query = "SELECT CONTRACT.CONTRACTID,CONTRACT.SIGN_DATE,CUSTOMER.BIRTHDAY, CUSTOMER.NAME,RESIDENT_REGISTRATION.PERMANENT_RESIDENT,CUSTOMER.PHONE,ROOM.LOCATION,CONTRACT.PRICE,CONTRACT.ELECTRICPRICE,CONTRACT.WATERPRICE,CONTRACT.DEPOSIT FROM CONTRACT INNER JOIN ROOM ON CONTRACT.ROOM_ID=ROOM.ROOMID INNER JOIN RESIDENT_REGISTRATION ON CONTRACT.CUSTOMER_ID=RESIDENT_REGISTRATION.CCCD INNER JOIN CUSTOMER ON CUSTOMER.CONTRACTID=CONTRACT.CONTRACTID WHERE CONTRACT.CONTRACTID=?";
       try {
           PreparedStatement ps = connection.prepareStatement(query);
           ps.setInt(1, contractID);
           ResultSet resultSet = ps.executeQuery();
           ContractDetail contractDetail = new ContractDetail();
           while (resultSet.next())
           {
               contractDetail.setSign_date(resultSet.getDate("SIGN_DATE"));
               contractDetail.setBirthDay(resultSet.getDate(""));
               contractDetail.setBirthDay(resultSet.getDate("CUSTOMER.BIRTHDAY"));
               contractDetail.setPermanent_resident(resultSet.getString("permanent_resident"));
               contractDetail.setPhone(resultSet.getString("CUSTOMER.PHONE"));
               String[] location = resultSet.getString("ROOM.LOCATION").split(",");
               contractDetail.setLocation(new Location(location[0],location[1],location[2],location[3]));
               contractDetail.setPrice(resultSet.getInt("PRICE"));
               contractDetail.setElectricPrice(resultSet.getInt("CONTRACT.ELECTRICPRICE"));
               contractDetail.setWaterPrice(resultSet.getInt("CONTRACT.WATERPRICE"));
               contractDetail.setDeposit(resultSet.getInt("CONTRACT.DEPOSIT"));
               contractDetail.setCustomer_Name(resultSet.getString("CUSTOMER.NAME"));
              
           }
            return contractDetail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }
}
