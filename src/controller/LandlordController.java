/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.LandLordDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import model.LandLord;
import view.LandLordView;
import view.RequestCreateRoom;

/**
 *
 * @author Admin
 */
public class LandlordController {
    private LandLordView view;
    private LandLordDAO DAO;
    private LandLord model;
    private RequestCreateRoom rqView;
    public LandlordController(LandLordView view,LandLord model){
        this.view = view;
        this.model = model;
        DAO = new LandLordDAO();
        view.setActionListenerForBtnCreateRoom(new ClickCreateRoom());
    }
    class ClickCreateRoom implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             rqView = new RequestCreateRoom();
        }
        
    }
}
