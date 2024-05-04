/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ResidentDetail {
    private Long CCCD;
    private String current_resident;
    private String temporary_accommodation;
    private String permanent_resident;
    private String current_work;

    public Long getCCCD() {
        return CCCD;
    }

    public void setCCCD(Long CCCD) {
        this.CCCD = CCCD;
    }

    public String getCurrent_resident() {
        return current_resident;
    }

    public void setCurrent_resident(String current_resident) {
        this.current_resident = current_resident;
    }

    public String getTemporary_accommodation() {
        return temporary_accommodation;
    }

    public void setTemporary_accommodation(String temporary_accommodation) {
        this.temporary_accommodation = temporary_accommodation;
    }

    public String getPermanent_resident() {
        return permanent_resident;
    }

    public void setPermanent_resident(String permanent_resident) {
        this.permanent_resident = permanent_resident;
    }

    public String getCurrent_work() {
        return current_work;
    }

    public void setCurrent_work(String current_work) {
        this.current_work = current_work;
    }
    
}
