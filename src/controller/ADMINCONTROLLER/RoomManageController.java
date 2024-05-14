/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.ContractDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Room;
import view.admin.AdminRoomDetail;
import view.admin.ContractLandlordDetailView;
import view.admin.RoomManage;

/**
 *
 * @author Admin
 */
public class RoomManageController {
     private final RoomManage view;
     private final RoomDAO DAO;
     private ContractDAO contractDAO;
     private ArrayList<Room> rooms;
     private ContractLandlordDetailView rootContractView;
     private AdminRoomDetail roomdetail;
     public RoomManageController(RoomManage view){
         this.view = view;
         DAO = new RoomDAO();
         rooms = DAO.getAllRoomNoMatter();
         contractDAO = new ContractDAO();
         initData();view.setActionListenerFortablebtn(new ClickRoot(), new ClickDelete(), new ClickEdit());
     }
     public RoomManage Render(){
         return view;
     }
     private void initData(){
         view.initTable(rooms);
     }
     class ClickDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                  boolean rs = DAO.updateStatusRoom(rooms.get(view.getSelectionIndex()).getID(),"XÓA");
                  if(rs){
                      JOptionPane.showMessageDialog(view, "Xóa thành công");
                  }
                  else JOptionPane.showMessageDialog(view, "Xóa thất bại, vui lòng thử lại sau");
             }
  }
  class ClickRoot implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             rootContractView = new ContractLandlordDetailView();
                   rootContractView.initData(contractDAO.getContractLL(rooms.get(view.getSelectionIndex()).getLandLordContractID()));
                   java.awt.EventQueue.invokeLater(() -> {
                   rootContractView.setDefaultCloseOperation(ContractLandlordDetailView.DISPOSE_ON_CLOSE);
                   rootContractView.setVisible(true);
            });
                   rootContractView.setBtnUpdate(new UpdateClick());
        }
  }
  class ClickEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             roomdetail = new AdminRoomDetail();
             roomdetail.initData(rooms.get(view.getSelectionIndex()));
             java.awt.EventQueue.invokeLater(() -> {
                   roomdetail.setDefaultCloseOperation(ContractLandlordDetailView.DISPOSE_ON_CLOSE);
                   roomdetail.setVisible(true);
            });
                   roomdetail.setBtnUpdate(new UpdateClick());
        }
  }
  class UpdateClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             boolean result = DAO.updateDataRoom(roomdetail.UpdateData());
             if(result) JOptionPane.showMessageDialog(roomdetail, "Đã cập nhật thành công");
             else JOptionPane.showMessageDialog(roomdetail, "Cập nhật thất bại, vui lòng thử lại sau !!");
             roomdetail.dispose();
        }
  }
}
