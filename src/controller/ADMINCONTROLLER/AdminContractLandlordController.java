/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.ContractDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
      private ArrayList<Contract_Landlord> contracts;
      public AdminContractLandlordController(ContractLandlord view){
          this.view = view;
          DAO = new ContractDAO();
          initData();
           view.setActionListenerFortablebtn(new ClickAccept(), new ClickDelete(), new ClickEdit());
      }
      public ContractLandlord Render(){
          return view;
      }
      private void initData(){
          contracts = DAO.getAllContract_Landlords();
          view.initDatable(contracts);
      }
       class ClickAccept implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectionIndex();
            if (contracts.get(index).getStatus().equals("XÓA")){
               //
            }
              DAO.UpdateStatusContractLandLord(contracts.get(view.getSelectionIndex()).getID(),"ĐÃ DUYỆT");
        }
  }
  class ClickDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               int index = view.getSelectionIndex();
               contracts.remove(index);
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
}
