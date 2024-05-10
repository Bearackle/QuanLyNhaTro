/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Bill {
    private int ID;
    private Long customer_id;
    private Date dateCreated;
    private Date pay_date;
    private String status;
    private int price;
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Calendar getCalendar(Date date)
     {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         return calendar;
     }
}
