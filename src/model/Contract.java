/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Contract {
    private int ID;
    private int CustomerCCCD;
    private String CustomerName;
    private int RoomID;
    private int Duration; //tinh bang thang thang
    private int Price;
    private String Signed_date;
    private boolean Status;
    private int ElecticPrice;
    private int WaterPrice;
    private int Deposit;
    private String EnterDate;
    private String CancelDate;

    public String getEnterDate() {
        return EnterDate;
    }

    public void setEnterDate(String EnterDate) {
        this.EnterDate = EnterDate;
    }

    public String getCancelDate() {
        return CancelDate;
    }

    public void setCancelDate(String CancelDate) {
        this.CancelDate = CancelDate;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerCCCD() {
        return CustomerCCCD;
    }

    public void setCustomerCCCD(int CustomerCCCD) {
        this.CustomerCCCD = CustomerCCCD;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int RoomID) {
        this.RoomID = RoomID;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getSigned_date() {
        return Signed_date;
    }

    public void setSigned_date(String Signed_date) {
        this.Signed_date = Signed_date;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getElecticPrice() {
        return ElecticPrice;
    }

    public void setElecticPrice(int ElecticPrice) {
        this.ElecticPrice = ElecticPrice;
    }

    public int getWaterPrice() {
        return WaterPrice;
    }

    public void setWaterPrice(int WaterPrice) {
        this.WaterPrice = WaterPrice;
    }

    public int getDeposit() {
        return Deposit;
    }

    public void setDeposit(int Deposit) {
        this.Deposit = Deposit;
    }
    
}

class ContractOwner extends Contract
{
     private int numberofRoom;
    public int getNumberofRoom() {
        return numberofRoom;
    }

    public void setNumberofRoom(int numberofRoom) {
        this.numberofRoom = numberofRoom;
    }
}
