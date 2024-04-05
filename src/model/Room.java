/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Room {
    private int ID; // khoa chinh
    private String Name;
    private int Prices;
    private double Area;
    private Location location;
    private String Decription;
    private double ElecticPrices;
    private double WaterPrices;
    private double GarbagePrices;
    private double WifiPrices;
    private String CustomerName;
    private String IconList;
    public void setIconList(String IconList)
    {
        this.IconList = IconList;
    }
    
    public String getIconList(){
            return this.IconList;
    }
    public String getCustomerName()
    {
        return CustomerName;
    }
    public void setCustomerName(String CustomerName)
    {
        this.CustomerName = CustomerName;
    }
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPrices() {
        return Prices;
    }

    public void setPrices(int Prices) {
        this.Prices = Prices;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String Decription) {
        this.Decription = Decription;
    }

    public double getElecticPrices() {
        return ElecticPrices;
    }

    public void setElecticPrices(double ElecticPrices) {
        this.ElecticPrices = ElecticPrices;
    }

    public double getWaterPrices() {
        return WaterPrices;
    }

    public void setWaterPrices(double WaterPrices) {
        this.WaterPrices = WaterPrices;
    }

    public double getGarbagePrices() {
        return GarbagePrices;
    }

    public void setGarbagePrices(double GarbagePrices) {
        this.GarbagePrices = GarbagePrices;
    }

    public double getWifiPrices() {
        return WifiPrices;
    }

    public void setWifiPrices(double WifiPrices) {
        this.WifiPrices = WifiPrices;
    }
   public class Location {
    private String Cty;
    private String District;
    private String Ward;
    private String Street;

    public String getCty() {
        return Cty;
    }

    public void setCty(String Cty) {
        this.Cty = Cty;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String Ward) {
        this.Ward = Ward;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }   
}
}
