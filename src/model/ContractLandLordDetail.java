/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ContractLandLordDetail {
    private int ID;
    private LocalDate signed_Date;
    private String status;
    private String RoomName;
    private String LandlordName;
    public String getLandlordName() {
        return LandlordName;
    }

    public void setLandlordName(String LandlordName) {
        this.LandlordName = LandlordName;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getSigned_Date() {
        return signed_Date;
    }
    public void setSigned_Date(LocalDate signed_Date) {
        this.signed_Date = signed_Date;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }
}
