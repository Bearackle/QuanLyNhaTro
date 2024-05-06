/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Customer;
import model.Room;
import model.RoomService;
import view.RoomServiceView;
import view.TimePicker;

/**
 *
 * @author Admin
 */
public class ServicesController {
    private final RoomServiceView view;
    private final RoomDAO DAO;
    private final Room room;
    private TimePicker tp;
    private final RoomService model;
    public ServicesController (RoomServiceView view,Customer input){
        this.view = view;
        DAO = new RoomDAO();
        room = DAO.getDataRoomWithCustomerID(input.getCCCD());
        model = new RoomService();
        view.setActionListenerForClean(new RequestClean());
        view.setActionListenerForFix(new RequestFix());
        tp = new TimePicker();
        tp.setActionListenerForOK(new ClickOK());
    }
    public RoomServiceView Render(){
        return view;
    }
    class RequestClean implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(room.getCategoryId() == 600) {
                JOptionPane.showMessageDialog(view,"Không thể yêu cầu dịch vụ này được");
                return;
            }
            tp.setContent("DỌN PHÒNG Ở");
            model.setPrice(200000);
            java.awt.EventQueue.invokeLater(() -> {
                tp.setVisible(true);
                tp.setDefaultCloseOperation(TimePicker.DISPOSE_ON_CLOSE); 
            });
            
        }
    }
    class RequestFix implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            tp.setContent("");
            model.setPrice(0);
            java.awt.EventQueue.invokeLater(() -> {
                tp.setVisible(true);
                tp.setDefaultCloseOperation(TimePicker.DISPOSE_ON_CLOSE); 
            });
        }
    }
    class ClickOK implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRoomID(room.getID());
            model.setDescription(tp.getContent());
            model.setCreateDate(tp.getSelectedDate());
            if(DAO.RequestRoomService(model)){
                JOptionPane.showMessageDialog(view, "Bạn đã yêu cầu dịch vụ thành công, Xin cảm ơn!!");
            }
            else
            JOptionPane.showMessageDialog(view, "Yêu cầu dịch vụ không thành công, Vui lòng liên hệ với admin!!");
            tp.dispose();
        }
    }
}
