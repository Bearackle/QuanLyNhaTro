/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Contract {
    private int ID;
    private Long CustomerCCCD;
    private String CustomerName;
    private int RoomID;
    private int Duration; //tinh bang thang thang
    private int Price;
    private LocalDate Signed_date;
    private String Status;
    private int ElecticPrice;
    private int WaterPrice;
    private int Deposit;
    private LocalDate EnterDate;
    private LocalDate CancelDate;
    private int NumberOfPeople;

    public int getNumberOfPeople() {
        return NumberOfPeople;
    }

    public void setNumberOfPeople(int NumberOfPeople) {
        this.NumberOfPeople = NumberOfPeople;
    }
    
    public LocalDate getEnterDate() {
        return EnterDate;
    }

    public void setEnterDate(LocalDate EnterDate) {
        this.EnterDate = EnterDate;
    }

    public LocalDate getCancelDate() {
        return CancelDate;
    }

    public void setCancelDate(LocalDate CancelDate) {
        this.CancelDate = CancelDate;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Long getCustomerCCCD() {
        return CustomerCCCD;
    }

    public void setCustomerCCCD(Long CustomerCCCD) {
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

    public LocalDate getSigned_date() {
        return Signed_date;
    }

    public void setSigned_date(LocalDate Signed_date) {
        this.Signed_date = Signed_date;
    }

    public String isStatus() {
        return Status;
    }

    public void setStatus(String Status) {
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
    public static Calendar getCalendar(Date date)
     {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         return calendar;
     }
}

