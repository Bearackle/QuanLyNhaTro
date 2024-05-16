/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.RoomService;
import view.Room.RoomServiceView;
import view.admin.RoomServiceDetail;

/**
 *
 * @author Admin
 */
public class AdminRoomServices {
    private RoomServiceView view;
    private RoomDAO DAO;
    private RoomServiceDetail editView;
    private ArrayList<RoomService> list;
    public AdminRoomServices (RoomServiceView view){
        this.view = view;
        DAO = new RoomDAO();
        view.hideButton();
        view.setActionListenerForbtnPay(new UpdateClick());
        initData();
    }
    public RoomServiceView Render(){
        return view;
    }
    private void initData(){
         list = DAO.GetAllRoomServiceNoMatter();
         view.initTable(list);
    }
    class UpdateClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            editView = new RoomServiceDetail();
            editView.initData(list.get(view.getSelectedItemTable()));
            java.awt.EventQueue.invokeLater(() -> {
                   editView.setDefaultCloseOperation(RoomServiceDetail.DISPOSE_ON_CLOSE);
                   editView.setVisible(true);
            });
            editView.setBtnUpdate(new ClickUpdate());
        }
    }
    class ClickUpdate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             if(DAO.updateStatusRoomService(editView.getUpdateData())){
                 JOptionPane.showMessageDialog(editView, "Cập nhật trạng thái thành công!");
             }else 
                   JOptionPane.showMessageDialog(editView, "Cập nhật trạng thái thất bại!");
             editView.dispose();
        }
    }
}
