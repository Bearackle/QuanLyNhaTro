/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.ContractDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.mail.FetchProfile;
import javax.swing.JOptionPane;
import model.Contract_Landlord;
import view.admin.ContractLandlord;
import view.admin.ContractLandlordDetailView;

/**
 *
 * @author Admin
 */
public class AdminContractLandlordController {
      private ContractLandlord view;
      private ContractLandlordDetailView editView;
      private ContractDAO DAO;
      private RoomDAO roomDAO;
      private ArrayList<Contract_Landlord> contracts;
      public AdminContractLandlordController(ContractLandlord view){
          this.view = view;
          DAO = new ContractDAO();
          roomDAO = new RoomDAO();
          initData("XÓA");
          view.setActionListenerFortablebtn(new ClickAccept(), new ClickDelete(), new ClickEdit());
          view.setTggleBtn(new ShowAll());
      }
      public ContractLandlord Render(){
          return view;
      }
      private void initData(String status){
          contracts = DAO.getAllContract_Landlords();
          contracts.removeIf(con -> !"".equals(con.getStatus()) ? con.getStatus().equals(status) : false);
          view.initDatable(contracts);
      }
       class ClickAccept implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectionIndex();
            if (contracts.get(index).getStatus().equals("XÓA")){
                roomDAO.UpdateStatusRoomThroughLandlordcontractid(contracts.get(index).getID(), "XÓA");
                JOptionPane.showMessageDialog(view, "Cập nhật thành công");
            } else if(contracts.get(index).getStatus().equals("CHỜ DUYỆT"))
            {
              DAO.UpdateStatusContractLandLord(contracts.get(view.getSelectionIndex()).getID(),"ĐÃ DUYỆT");
              roomDAO.UpdateStatusRoomThroughLandlordcontractid(contracts.get(index).getID(),"TRỐNG");
            JOptionPane.showMessageDialog(view, "Cập nhật thành công");
            }
        }
  }
  class ClickDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               int index = view.getSelectionIndex();
               contracts.remove(index);
               DAO.UpdateStatusContractLandLord(contracts.get(index).getID(),"XÓA");
               view.initDatable(contracts);          
             }
  }
  class ClickEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked");
                   editView = new ContractLandlordDetailView();
                   editView.initData(contracts.get(view.getSelectionIndex()));
                   java.awt.EventQueue.invokeLater(() -> {
                   editView.setDefaultCloseOperation(ContractLandlordDetailView.DISPOSE_ON_CLOSE);
                   editView.setVisible(true);
            });
                    editView.setBtnUpdate(new UpdateClick());
        }
  }
  class UpdateClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean result = DAO.UpdateContractLandlord(editView.getUpdateData());
            if(result) {     
                JOptionPane.showMessageDialog(editView, "Cập nhật thành công");
            }
            else 
                JOptionPane.showMessageDialog(editView, "Cập nhật thất bại, vui lòng thử lại sau");
            editView.dispose();
        }
  }
  class ShowAll implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                initData("");
                view.setTggleText("Rút gọn");
            }
            else {
                initData("XÓA");
                view.setTggleText("Xem tất cả");
            }
        }
      
  }
}
