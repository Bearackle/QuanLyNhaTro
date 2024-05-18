/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Review {
    private int Roomid;
    private Long Customerid;
    private String Content;
    private int Vote;
    private String CustmerName;

    public String getCustmerName() {
        return CustmerName;
    }
    public void setCustmerName(String CustmerName) {
        this.CustmerName = CustmerName;
    }
    public int getRoomid() {
        return Roomid;
    }

    public void setRoomid(int Roomid) {
        this.Roomid = Roomid;
    }

    public Long getCustomerid() {
        return Customerid;
    }

    public void setCustomerid(Long Customerid) {
        this.Customerid = Customerid;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getVote() {
        return Vote;
    }

    public void setVote(int Vote) {
        this.Vote = Vote;
    }
    
}
