/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.*;
import java.time.LocalDate;
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
        String query = "SELECT * FROM CONTRACT WHERE CUSTOMER_ID=? AND STATUS='ĐÃ DUYỆT'";
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
                contract.setSigned_date(result.getDate("SIGN_DATE").toLocalDate());
                contract.setStatus(result.getString("STATUS"));
                contract.setElecticPrice(result.getInt("ELECTRICPRICE"));
                contract.setWaterPrice(result.getInt("WATERPRICE"));
                contract.setDeposit(result.getInt("DEPOSIT"));
                contract.setEnterDate(result.getDate("ENTER_DATE").toLocalDate());
                contract.setCancelDate(result.getDate("CANCEL_DATE").toLocalDate());
  
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
                contract.setSigned_date(resultSet.getDate("SIGN_DATE").toLocalDate());
                contract.setStatus(resultSet.getString("STATUS"));
                contract.setElecticPrice(resultSet.getInt("ELECTRICPRICE"));
                contract.setWaterPrice(resultSet.getInt("WATERPRICE"));
                contract.setEnterDate(resultSet.getDate("ENTER_DATE").toLocalDate());
                contract.setCancelDate(resultSet.getDate("CANCEL_DATE").toLocalDate());
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
               contractDetail.setSign_date(resultSet.getDate("SIGN_DATE").toLocalDate());
               contractDetail.setBirthDay(resultSet.getDate("BIRTHDAY").toLocalDate());
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
    public int CreateNewLandLordContractAndRoom(Contract_Landlord contract,Room room){
        String query1 = "INSERT INTO CONTRACT_LANDLORD (LANDLORDID,SIGNED_DATE,STATUS,DURATION) VALUES(?,?,?,?)";
        String query2 = "INSERT INTO ROOM (NAME,AREA,LOCATION,PRICE,DESCRIPTION,STATUS,CATEGORYID,LANDLORDCONTRACTID,VOTE,ISALLOWMATCH) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try{
        String returnCols[] = {"CONTRACTID"};
        String returnCols2[] = {"ROOMID"};
        PreparedStatement ps1 = connection.prepareStatement(query1,returnCols);
        PreparedStatement ps2 = connection.prepareStatement(query2,returnCols2);
        ps1.setLong(1,contract.getLandlordID());
        ps1.setDate(2, java.sql.Date.valueOf(contract.getSigned_date()));
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
        String query = "SELECT CONTRACTID,NAME,SIGNED_DATE,CONTRACT_LANDLORD.STATUS FROM ROOM INNER JOIN CONTRACT_LANDLORD ON LANDLORDCONTRACTID = CONTRACTID WHERE LANDLORDID=? AND CONTRACT_LANDLORD.STATUS != 'XÓA'";
        ArrayList<ContractLandLordDetail> contracts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
             ps.setLong(1, LandlordID);
             ResultSet resultSet = ps.executeQuery();
             while(resultSet.next()){
                 ContractLandLordDetail contract = new ContractLandLordDetail();
                 contract.setID(resultSet.getInt(1));
                 contract.setRoomName(resultSet.getString(2));
                 contract.setSigned_Date(resultSet.getDate(3).toLocalDate());
                 contract.setStatus(resultSet.getString(4));
                 contracts.add(contract);
             }
             return contracts;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean UpdateStatusContractLandLord (int ID,String status){
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
    public boolean updateStatusContractCustomer (int ID, String status){
        String query = "UPDATE CONTRACT SET STATUS=? WHERE CONTRACTID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2,ID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Contract> getAllContracts(){
        String query = "SELECT * FROM CONTRACT";
        ArrayList<Contract> list = new ArrayList<>();
        try{
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                  Contract contract = new Contract();
                  contract.setID(rs.getInt(1));
                  contract.setCustomerCCCD(rs.getLong(2));
                  contract.setCustomerName(rs.getString(3));
                  contract.setRoomID(rs.getInt(4));
                  contract.setDuration(rs.getInt(5));
                  contract.setPrice(rs.getInt(6));
                  contract.setSigned_date(rs.getDate(7).toLocalDate());
                  contract.setStatus(rs.getString(8));
                  contract.setElecticPrice(rs.getInt(9));
                  contract.setWaterPrice(rs.getInt(10));
                  contract.setEnterDate(rs.getDate(11).toLocalDate());
                  contract.setCancelDate(rs.getDate(12) ==null ? LocalDate.now() : rs.getDate(12).toLocalDate());
                  contract.setDeposit(rs.getInt(13));
                  contract.setNumberOfPeople(rs.getInt(14));
               list.add(contract);
             } 
             return list;
        } catch (SQLException e){
            e.printStackTrace();
            }
        return null;
    }
    public boolean UpdateStatusRoomAndContractCustomer(String status, int ID){
           String query = "UPDATE CONTRACT SET STATUS=? WHERE CONTRACTID=?";
           try{
               PreparedStatement ps = connection.prepareStatement(query);
               ps.setString(1, status);
               ps.setInt(2, ID);
               ps.executeUpdate();
               return true;
           } catch (SQLException e){
               e.printStackTrace();
           }
           return false;
    }
    public boolean UpdateStatusRoom(String status, int ID){
           String query = "UPDATE ROOM SET STATUS=? WHERE ROOMID=?";
           try{
               PreparedStatement ps = connection.prepareStatement(query);
               ps.setString(1, status);
               ps.setInt(2, ID);
               ps.executeUpdate();
               return true;
           } catch (SQLException e){
               e.printStackTrace();
           }
           return false;
    }
    public boolean UpdateContract(Contract contract){
         String query = "UPDATE CONTRACT SET CUSTOMER_ID=?,CUSTOMER_NAME=?,ROOM_ID=?,DURATION=?,PRICE=?,SIGN_DATE=?,STATUS=?,ELECTRICPRICE=?,WATERPRICE=?,ENTER_DATE=?,CANCEL_DATE=?,DEPOSIT=?,NUMBEROFPEOPLE=? WHERE CONTRACTID=?";
         try{
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setLong(1, contract.getCustomerCCCD());
             ps.setString(2,contract.getCustomerName());
             ps.setInt(3, contract.getRoomID());
             ps.setInt(4, contract.getDuration());
             ps.setInt(5, contract.getPrice());
             ps.setDate(6, java.sql.Date.valueOf(contract.getSigned_date()));
             ps.setString(7, contract.isStatus());
             ps.setInt(8, contract.getElecticPrice());
             ps.setInt(9, contract.getWaterPrice());
             ps.setDate(10, java.sql.Date.valueOf(contract.getEnterDate()));
             ps.setDate(11, java.sql.Date.valueOf(contract.getCancelDate()));
             ps.setInt(12, contract.getDeposit());
             ps.setInt(13, contract.getNumberOfPeople());
             ps.setInt(14, contract.getID());
             ps.executeUpdate();
             return true;
         } catch(SQLException e){
             e.printStackTrace();
         }
         return false;
    }
    public boolean CreateNewCustomerContract(Contract contract){
        String query = "INSERT INTO CONTRACT (CUSTOMER_ID,CUSTOMER_NAME,ROOM_ID,DURATION,PRICE,SIGN_DATE,STATUS,ELECTRICPRICE,WATERPRICE,ENTER_DATE,CANCEL_DATE,DEPOSIT,NUMBEROFPEOPLE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, contract.getCustomerCCCD());
            ps.setString(2, contract.getCustomerName());
            ps.setInt(3, contract.getRoomID());
            ps.setInt(4, contract.getDuration());
            ps.setInt(5, contract.getPrice());
            ps.setDate(6,java.sql.Date.valueOf(contract.getSigned_date()));
            ps.setString(7, contract.isStatus());
            ps.setInt(8, contract.getElecticPrice());
            ps.setInt(9, contract.getWaterPrice());
            ps.setDate(10, java.sql.Date.valueOf(contract.getEnterDate()));
            ps.setDate(11, java.sql.Date.valueOf(contract.getCancelDate()));
            ps.setInt(12, contract.getDeposit());
            ps.setInt(13, contract.getNumberOfPeople());
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<Contract_Landlord> getAllContract_Landlords(){
        String query = "SELECT * FROM CONTRACT_LANDLORD";
        try{ 
            ArrayList<Contract_Landlord> list = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Contract_Landlord con = new Contract_Landlord();
                con.setID(rs.getInt(1));
                con.setLandlordID(rs.getLong(2));
                con.setSigned_date(rs.getDate(3).toLocalDate());
                con.setStatus(rs.getString(4));
                con.setDuration(rs.getInt(5));
              list.add(con);
            }
            return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean UpdateContractLandlord(Contract_Landlord contract){
        String query = "UPDATE CONTRACT_LANDLORD SET SIGNED_DATE=?,STATUS=?,DURATION=? WHERE CONTRACTID=?";
        try{
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setDate(1, java.sql.Date.valueOf(contract.getSigned_date()));
             ps.setString(2, contract.getStatus());
             ps.setInt(3, contract.getDuration());
             ps.setInt(4, contract.getID());
             ps.executeUpdate();
             return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public Contract_Landlord getContractLL(int ID){
        String query = "SELECT * FROM CONTRACT_LANDLORD WHERE CONTRACTID=?";        
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            Contract_Landlord con = new Contract_Landlord();
            if(rs.next()){   
                con.setID(rs.getInt(1));
                con.setLandlordID(rs.getLong(2));
                con.setSigned_date(rs.getDate(3).toLocalDate());
                con.setStatus(rs.getString(4));
                con.setDuration(rs.getInt(5));
            }
            return con;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}