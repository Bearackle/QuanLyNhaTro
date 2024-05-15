/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;
import model.BillCustomerDetail;
import model.BillLandlordDetail;
import model.LandLord;
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
                Bill bill = new BillCustomerDetail();
                bill.setID(rs.getInt(1));
                bill.setCustomer_id(rs.getLong(2));
                bill.setDateCreated(rs.getDate(3).toLocalDate());
                bill.setPay_date(rs.getDate(4).toLocalDate());
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
    public ArrayList<Bill> getAllBillofAContractLandlord(int contractid){
        String query = "SELECT BILL.BILL_ID,PAY_DATE,PRICE,Bill.STATUS FROM BILL INNER JOIN (SELECT BILL_ID,PRICE FROM BILL_LANDLORD WHERE CONTRACT_ID=?) BLL ON BILL.BILL_ID = BLL.BILL_ID";
       ArrayList <Bill> list = new ArrayList<>();
        try{
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, contractid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Bill bill = new BillLandlordDetail();
            bill.setID(rs.getInt(1));
            bill.setPay_date(rs.getDate(2).toLocalDate());
            bill.setPrice(rs.getInt(3));
            bill.setStatus(rs.getString(4));
            list.add(bill);
        }
        return list;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Bill> getAllBillNoMatter(){
        String query1 = "SELECT BILL.BILL_ID,CUSTOMER_ID,DATE_CREATED,PAY_DATE,STATUS,PRICE FROM BILL INNER JOIN BILL_CUSTOMER ON BILL.BILL_ID=BILL_CUSTOMER.BILL_ID";
        String query2 = "SELECT BILL.BILL_ID,CUSTOMER_ID,DATE_CREATED,PAY_DATE,STATUS, PRICE, CONTRACT_ID FROM BILL INNER JOIN BILL_LANDLORD ON BILL.BILL_ID=BILL_LANDLORD.BILL_ID";
        ArrayList<Bill> bills = new ArrayList<>();
        try{
            PreparedStatement ps1 = connection.prepareStatement(query1);
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                BillCustomerDetail bill = new BillCustomerDetail();
                bill.setID(rs.getInt(1));
                bill.setCustomer_id(rs.getLong(2));
                bill.setDateCreated(rs.getDate(3).toLocalDate());
                bill.setPay_date(rs.getDate(4).toLocalDate());
                bill.setStatus(rs.getString(5));
                bill.setPrice(rs.getInt(6));
                bills.add(bill);
            }
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                BillLandlordDetail bill = new BillLandlordDetail();
                bill.setID(rs2.getInt(1));
                bill.setCustomer_id(rs2.getLong(2));
                bill.setDateCreated(rs2.getDate(3).toLocalDate());
                bill.setPay_date(rs2.getDate(4).toLocalDate());
                bill.setStatus(rs2.getString(5));
                bill.setPrice(rs2.getInt(6));
                bill.setContract_ID(rs2.getInt(7));
                bills.add(bill);
            }
            return bills;
         } catch (SQLException e){
             e.printStackTrace();
         }
        return null;
    }
    public boolean updateBillStatus(String status, int ID){
        String query = "UPDATE BILL SET STATUS=? WHERE BILL_ID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, ID);
            ps.executeUpdate();
            return true;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public LandLord getLandlordInfo(int ID){
        String query = "SELECT CCCD,NAME,PHONE,BANKACCOUNT FROM CONTRACT_LANDLORD INNER JOIN LANDLORD ON LANDLORDID=CCCD WHERE CONTRACTID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            LandLord landlord = new LandLord();
            if(rs.next()){
                landlord.setCCCD(rs.getLong(1));
                landlord.setName(rs.getString(2));
                landlord.setPhone(rs.getString(3));
                landlord.setBankAccount(rs.getString(4));
            }
            return landlord;
            } catch (SQLException e){
                        e.printStackTrace();
                    }
            return null;
    }
    public int CreateCustomerBill(BillCustomerDetail bill){
        String query = "INSERT INTO BILL(CUSTOMER_ID,DATE_CREATED, PAY_DATE,STATUS) VALUES(?,?,?,?)";
        try{
            String[] cols = {"BILL_ID"};
            PreparedStatement ps = connection.prepareStatement(query,cols);
            ps.setLong(1, bill.getCustomer_id());
            ps.setDate(2, java.sql.Date.valueOf(bill.getDateCreated()));
            ps.setDate(3, java.sql.Date.valueOf(bill.getPay_date()));
            ps.setString(4, bill.getStatus());
            int keyGen;
            int row = ps.executeUpdate();
                    if (row==0) throw new SQLException("Fail create Bill");
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    keyGen = rs.getInt(1);
                } else {
                    throw new SQLException("Fail getting key");
                }
            }
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO BILL_CUSTOMER(BILL_ID,WATER,ELECTRIC,WIFI,GARBAGE,PRICE,ROOMID) VALUES (?,?,?,?,?,?,?)");
            int[] arrContractPrice = getContractPricedetail(bill.getRoomid(), bill.getCustomer_id());
            int[] arrPreviousPrice = getCustomerDetailLatest(bill);
            int fee = (bill.getWater()-arrPreviousPrice[0])* arrContractPrice[0] + (bill.getElectric()-arrPreviousPrice[1])*arrContractPrice[1] +
                    bill.getGarbage() + bill.getWifi();
            ps1.setInt(1, keyGen);
            ps1.setInt(2, bill.getWater());
            ps1.setInt(3, bill.getElectric());
            ps1.setInt(4, bill.getWifi());
            ps1.setInt(5, bill.getGarbage());
            ps1.setInt(6, fee + getPrice(bill.getRoomid())[1]);
            ps1.setInt(7, bill.getRoomid());
            ps1.executeUpdate();
            return fee ;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public int[] getCustomerDetailLatest(BillCustomerDetail bill){
        String query = " SELECT WATER,ELECTRIC FROM BILL INNER JOIN BILL_CUSTOMER ON BILL.BILL_ID=BILL_CUSTOMER.BILL_ID WHERE CUSTOMER_ID=? AND ROOMID=? AND ROWNUM=1 ORDER BY DATE_CREATED DESC";
        try{
            int[] arr = new int[2];
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, bill.getCustomer_id());
            ps.setInt(2, bill.getRoomid());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                arr[0] = rs.getInt(1);
                arr[1] = rs.getInt(2);
                return arr;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return new int[]{0,0};
    }
    public int[] getContractPricedetail(int roomid, Long customerid){
        String query = "SELECT WATERPRICE,ELECTRICPRICE,PRICE FROM CONTRACT WHERE ROOM_ID=? AND CUSTOMER_ID=? AND STATUS='ĐÃ DUYỆT'";
        int[] arr = new int[3];
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, roomid);
            ps.setLong(2 ,customerid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                arr[0] = rs.getInt(1);
                arr[1] = rs.getInt(2);
                arr[2] = rs.getInt(3);
                return arr;
            }
        } catch(SQLException e){
            e.printStackTrace();  
        }
        return new int[]{0,0};
    }
    public boolean CreateNewBillLandlord(Bill bill,int Extrafee){
          String query = "INSERT INTO BILL(CUSTOMER_ID,DATE_CREATED, PAY_DATE,STATUS) VALUES(?,?,?,?)";
        try{
            String[] cols = {"BILL_ID"};
            PreparedStatement ps = connection.prepareStatement(query,cols);
            ps.setLong(1, getLandLordIdbyRoomid(bill.getRoomid()));
            ps.setDate(2, java.sql.Date.valueOf(bill.getDateCreated()));
            ps.setDate(3, java.sql.Date.valueOf(bill.getPay_date()));
            ps.setString(4,"CHƯA THANH TOÁN");
            int keyGen;
            int row = ps.executeUpdate();
                    if (row==0) throw new SQLException("Fail create Bill");
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    keyGen = rs.getInt(1);
                } else {
                    throw new SQLException("Fail getting key");
                }
            }
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO BILL_LANDLORD VALUES(?,?,?)");
            int[] arr = getPrice(bill.getRoomid());
            arr[1] = ((int) Math.round(0.7*arr[1])) + Extrafee;
            ps1.setInt(1, keyGen);
            ps1.setInt(2, arr[0]);
            ps1.setInt(3, arr[1]);
            ps1.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public int[] getPrice(int roomid){
         String query = "SELECT CT.CONTRACTID,R.PRICE,CT.LANDLORDID FROM ROOM R INNER JOIN CONTRACT_LANDLORD CT ON R.LANDLORDCONTRACTID=CT.CONTRACTID WHERE R.ROOMID=?";
         try{
             PreparedStatement ps = connection.prepareStatement(query);
             ps.setInt(1, roomid);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                  return new int[]{ rs.getInt(1),rs.getInt(2)};
             }
         }catch(SQLException e)
         {
             e.printStackTrace();
         }
         return new int[]{0,0,0};
    }
    public Long getLandLordIdbyRoomid(int Roomid){
        String query = "SELECT CT.LANDLORDID FROM ROOM R INNER JOIN CONTRACT_LANDLORD CT ON R.LANDLORDCONTRACTID=CT.CONTRACTID WHERE R.ROOMID=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Roomid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
