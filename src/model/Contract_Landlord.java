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
public class Contract_Landlord {
    private int ID;
    private Long LandlordID;
    private Date signed_date;
    private String status;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Long getLandlordID() {
        return LandlordID;
    }

    public void setLandlordID(Long LandlordID) {
        this.LandlordID = LandlordID;
    }

    public Date getSigned_date() {
        return signed_date;
    }

    public void setSigned_date(Date signed_date) {
        this.signed_date = signed_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
