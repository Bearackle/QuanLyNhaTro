/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import model.BillCustomerDetail;
/**
 *
 * @author Admin
 */
public class BillDAO {
    private Connection connection;
    public BillDAO(){
        connection = DataBaseConnection.getConnection();
    }
    public ArrayList<Bill> getAllBillbyCustomerID(Long ID){
        ArrayList<Bill> list = new ArrayList<>();
        String query = "SELECT * FROM BILL WHERE CUSTOMER_ID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Bill bill = new Bill();
                bill.setID(rs.getInt(1));
                bill.setCustomer_id(rs.getLong(2));
                bill.setDateCreated(rs.getDate(3));
                bill.setPay_date(rs.getDate(4));
                bill.setStatus(rs.getString(5));
            list.add(bill);
            }
          return list;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public BillCustomerDetail getBillCustomerByID(int ID){
        BillCustomerDetail bill = new BillCustomerDetail();
        String query = "SELECT WATER, ELECTRIC, WIFI,GARBAGE,PRICE FROM BILL_CUSTOMER WHERE BILL_ID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bill.setID(ID);
                bill.setWater(rs.getInt(1));
                bill.setElectric(rs.getInt(2));
                bill.setWifi(rs.getInt(3));
                bill.setGarbage(rs.getInt(4));
                bill.setPrice(rs.getInt(5));
                return bill;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
