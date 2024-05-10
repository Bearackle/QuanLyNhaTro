/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class BillCustomerDetail extends Bill {
    private int Water;
    private int Electric;
    private int wifi;
    private int garbage;

    public int getWater() {
        return Water;
    }

    public void setWater(int Water) {
        this.Water = Water;
    }

    public int getElectric() {
        return Electric;
    }

    public void setElectric(int Electric) {
        this.Electric = Electric;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getGarbage() {
        return garbage;
    }

    public void setGarbage(int garbage) {
        this.garbage = garbage;
    }    
}
