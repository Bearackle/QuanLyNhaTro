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
import model.ContractLandLordDetail;
import model.Contract_Landlord;
import model.Location;
import model.Room;
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
    public ContractDetail contractDetailCustomer(Long customerid)
    {
        String query = "SELECT CONTRACT.CONTRACTID,CONTRACT.SIGN_DATE, CUSTOMER.CCCD,BIRTHDAY, CONTRACT.DURATION, CUSTOMER.NAME,RESIDENT_REGISTRATION.PERMANENT_RESIDENT,CUSTOMER.PHONE,ROOM.LOCATION,CONTRACT.PRICE,CONTRACT.ELECTRICPRICE,CONTRACT.WATERPRICE,CONTRACT.DEPOSIT FROM CONTRACT INNER JOIN ROOM ON CONTRACT.ROOM_ID=ROOM.ROOMID INNER JOIN RESIDENT_REGISTRATION ON CONTRACT.CUSTOMER_ID=RESIDENT_REGISTRATION.CCCD INNER JOIN CUSTOMER ON CUSTOMER.CCCD=CONTRACT.CUSTOMER_ID WHERE CUSTOMER.CCCD=?";
       try {
           PreparedStatement ps = connection.prepareStatement(query);
           ps.setLong(1, customerid);
           ResultSet resultSet = ps.executeQuery();
           ContractDetail contractDetail = new ContractDetail();
           while (resultSet.next())
           {
               contractDetail.setCustomer_ID(resultSet.getLong("CCCD"));
               contractDetail.setSign_date(resultSet.getDate("SIGN_DATE"));
               contractDetail.setBirthDay(resultSet.getDate("BIRTHDAY"));
               contractDetail.setPermanent_resident(resultSet.getString("permanent_resident"));
               contractDetail.setPhone(resultSet.getString("PHONE"));
               String[] location = resultSet.getString("LOCATION").split(",");
               contractDetail.setLocation(new Location(location[0],location[1],location[2],location[3]));
               contractDetail.setPrice(resultSet.getInt("PRICE"));
               contractDetail.setElectricPrice(resultSet.getInt("ELECTRICPRICE"));
               contractDetail.setWaterPrice(resultSet.getInt("WATERPRICE"));
               contractDetail.setDeposit(resultSet.getInt("DEPOSIT"));
               contractDetail.setCustomer_Name(resultSet.getString("NAME"));   
               contractDetail.setDuration(resultSet.getInt("DURATION"));
           }
            return contractDetail;
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }
    public void CreateNewContract(Contract contract){
        
    } 
    public int CreateNewLandLordContractAndRoom(Contract_Landlord contract,Room room){
        String query1 = "INSERT INTO CONTRACT_LANDLORD (LANDLORDID,SIGNED_DATE,STATUS,DURATION) VALUES(?,?,?,?)";
        String query2 = "INSERT INTO ROOM (NAME,AREA,LOCATION,PRICE,DESCRIPTION,STATUS,CATEGORYID,LANDLORDCONTRACTID,VOTE,ISALLOWMATCH) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try{
        String returnCols[] = {"CONTRACTID"};
        String returnCols2[] = {"ROOMID"};
        PreparedStatement ps1 = connection.prepareStatement(query1,returnCols);
        PreparedStatement ps2 = connection.prepareStatement(query2,returnCols2);
        ps1.setLong(1,contract.getLandlordID());
        ps1.setDate(2, new java.sql.Date(contract.getSigned_date().getTime()));
        ps1.setString(3,contract.getStatus());
        ps1.setInt(4, contract.getDuration());
        int rowContract = ps1.executeUpdate();
        if (rowContract == 0)  throw new SQLException("Creating Contract landlord fail"); 
        try(ResultSet keyContract = ps1.getGeneratedKeys()){
            if (keyContract.next()){
                room.setLandLordContractID(keyContract.getInt(1));
            }
            else {
                 throw new SQLException("fail getting key");
            }
        }
        ps2.setString(1, room.getName());
        ps2.setDouble(2, room.getArea());
        ps2.setString(3 ,room.getLocation().toString());
        ps2.setInt(4,room.getPrices());
        ps2.setString(5, room.getDescription());
        ps2.setString(6, room.getStatus());
        ps2.setInt(7, room.getCategoryId());
        ps2.setInt(8, room.getLandLordContractID());
        ps2.setInt(9, room.getVote());
        ps2.setString(10, room.isIsAllowMatch());
        int rs2 = ps2.executeUpdate();
        if ( rs2 == 0) throw new SQLException("Creating Room with contract fail");
         try(ResultSet keyContract2 = ps2.getGeneratedKeys()){
            if (keyContract2.next()){
                room.setID(keyContract2.getInt(1));
            }
            else {
                 throw new SQLException("fail getting key");
            }
        }
        return room.getID();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public ArrayList<ContractLandLordDetail> getContractLandLordDetail(Long LandlordID){
        String query = "SELECT CONTRACTID,NAME,SIGNED_DATE,CONTRACT_LANDLORD.STATUS FROM ROOM INNER JOIN CONTRACT_LANDLORD ON LANDLORDCONTRACTID = CONTRACTID WHERE LANDLORDID=? AND (CONTRACT_LANDLORD.STATUS='ĐÃ DUYỆT' OR CONTRACT_LANDLORD.STATUS='GIA HẠN')";
        ArrayList<ContractLandLordDetail> contracts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
             ps.setLong(1, LandlordID);
             ResultSet resultSet = ps.executeQuery();
             while(resultSet.next()){
                 ContractLandLordDetail contract = new ContractLandLordDetail();
                 contract.setID(resultSet.getInt(1));
                 contract.setRoomName(resultSet.getString(2));
                 contract.setSigned_Date(resultSet.getDate(3));
                 contract.setStatus(resultSet.getString(4));
                 contracts.add(contract);
             }
             return contracts;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean UpdateStatusContract (int ID,String status){
        String query = "UPDATE CONTRACT_LANDLORD SET STATUS=? WHERE CONTRACTID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, ID);
            int row=ps.executeUpdate();
            if (row==0) throw new SQLException("Cant set status");
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
