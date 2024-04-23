/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.Room;
import view.CustomControl.BufferedImageCreator;
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
        roomDAO = new RoomDAO();
    }
    public RoomDetailController(Room room)
    {
        this(new RoomDetail());
        this.room = room;
        roomDetail.initData(this.room);
    }
    
    
    class clickForNextBtn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                
        }
        
    }
}
