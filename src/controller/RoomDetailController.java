/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RoomDAO;
import model.Room;
import view.RoomDetail;

/**
 *
 * @author Admin
 */
public class RoomDetailController {
    private RoomDetail roomDetail;
    private RoomDAO roomDAO;
    private Room room;
    public RoomDetailController(RoomDetail roomDetail){
        this.roomDetail = roomDetail;
    }
    public RoomDetailController(Room room)
    {
        this(new RoomDetail());
        this.room = room;
    }
    public void initData()
    {
        
    }
}
