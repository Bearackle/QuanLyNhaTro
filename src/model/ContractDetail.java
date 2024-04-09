/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ContractDetail {
    private Date Sign_date;
    private Long Customer_ID;
    private Date BirthDay;
    private String permanent_resident;
    private String Phone;
    private Location location;
    private int Price;
    private int ElectricPrice;
    private int WaterPrice;
    private int Deposit;
    private Date Enter_date;
    private String Customer_Name;
    private int Duration;
    public int getDuration() {
        return Duration;
    }

    public void setDuration(int Duration) {
        this.Duration = Duration;
    }
    
    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }
    
    public Date getSign_date() {
        return Sign_date;
    }

    public void setSign_date(Date Sign_date) {
        this.Sign_date = Sign_date;
    }

    public Long getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(Long Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date BirthDay) {
        this.BirthDay = BirthDay;
    }

    public String getPermanent_resident() {
        return permanent_resident;
    }

    public void setPermanent_resident(String permanent_resident) {
        this.permanent_resident = permanent_resident;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getElectricPrice() {
        return ElectricPrice;
    }

    public void setElectricPrice(int ElectricPrice) {
        this.ElectricPrice = ElectricPrice;
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

    public Date getEnter_date() {
        return Enter_date;
    }

    public void setEnter_date(Date Enter_date) {
        this.Enter_date = Enter_date;
    }
    
}
