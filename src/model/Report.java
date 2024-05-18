/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Report {
    private int RoomidReport;
    private Long CCCDR;
    private String Content;
    private boolean isSolve;

    public int getRoomidReport() {
        return RoomidReport;
    }

    public void setRoomid(int RoomidReport) {
        this.RoomidReport = RoomidReport;
    }

    public Long getCCCDR() {
        return CCCDR;
    }

    public void setCCCDR(Long CCCDR) {
        this.CCCDR = CCCDR;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public boolean IsSolve() {
        return isSolve;
    }

    public void setSolve(boolean isSolve) {
        this.isSolve = isSolve;
    }
    public Report(){
        isSolve = false;
    }
}
