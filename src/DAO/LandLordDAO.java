/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import model.LandLord;
/**
 *
 * @author Admin
 */
public class LandLordDAO {
    private Connection connection;
    private PreparedStatement ps;
    public LandLordDAO(){
        connection = DataBaseConnection.getConnection();
    }
    public LandLord getLandLord(String phone){
        try{
        ps = connection.prepareStatement("SELECT CCCD,LANDLORD.NAME,BANKACCOUNT FROM ACCOUNT INNER JOIN  LANDLORD ON ACCOUNT.PHONE=LANDLORD.PHONE WHERE LANDLORD.PHONE=?");
        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            LandLord landLord = new LandLord();
            landLord.setCCCD(rs.getLong(1));
            landLord.setName(rs.getString(2));
            landLord.setBankAccount(rs.getString(3));
            return landLord;
          }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean CreateNewLandLord (LandLord newLandLord){
        try{
        ps = connection.prepareStatement("INSERT INTO LANDLORD VALUES(?,?,?,?");
        ps.setLong(1, newLandLord.getCCCD());
        ps.setString(2, newLandLord.getName());
        ps.setString(3, newLandLord.getPhone());
        ps.setString(4, newLandLord.getBankAccount());
        ps.executeUpdate();
        return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
