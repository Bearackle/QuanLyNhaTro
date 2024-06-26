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
    private String Description;
    private double ElecticPrices;
    private double WaterPrices;
    private double GarbagePrices;
    private double WifiPrices;
    private int LandLordContractID;
    private String status;
    private String CustomerName;
    private String IconList;
    private int Vote;
    private int CategoryId;
    private String isAllowMatch;

    public int getLandLordContractID() {
        return LandLordContractID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setLandLordContractID(int LandLordContractID) {
        this.LandLordContractID = LandLordContractID;
    }
    
    public String isIsAllowMatch() {
        return isAllowMatch;
    }

    public void setIsAllowMatch(String isAllowMatch) {
        this.isAllowMatch = isAllowMatch;
    }
    
    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }
    
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
    public int getID() {
        return ID;
    }

    public void setID(int id) {
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Decription) {
        this.Description = Decription;
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

    public int getVote() {
        return Vote;
    }

    public void setVote(int Vote) {
        this.Vote = Vote;
    }
}
