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
import model.Contract;
import view.admin.ContractCustomer;
import view.admin.DetailContractCustomer;

/**
 *
 * @author Admin
 */
public class AdminContractController {
  private final ContractDAO DAO;
  private final ContractCustomer view;
  private ArrayList<Contract> list;
  private DetailContractCustomer editView;
  private RoomDAO roomDAO;
  public AdminContractController(ContractCustomer view){
      this.view = view;
      roomDAO = new RoomDAO();
      DAO = new ContractDAO();
      initData();
      view.setActionListenerFortablebtn(new ClickAccept(), new ClickDelete(), new ClickEdit());
  }
  private void initData(){
      list = DAO.getAllContracts();
      view.initDataTable(list);
  }
  public ContractCustomer render(){
      return view;
  }
  class ClickAccept implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectionIndex();
            if (list.get(index).isStatus().equals("XÓA")){
                DAO.UpdateStatusRoom("TRỐNG", list.get(index).getRoomID());
            }
              DAO.updateStatusContractCustomer(list.get(view.getSelectionIndex()).getID(),"ĐÃ DUYỆT");
        }
  }
  class ClickDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               int index = view.getSelectionIndex();
               list.remove(index);
               view.initDataTable(list);
               DAO.updateStatusContractCustomer(list.get(index).getID(), "XÓA");
             }
  }
  class ClickEdit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                   editView = new DetailContractCustomer();
                   editView.setData(list.get(view.getSelectionIndex()));
                     java.awt.EventQueue.invokeLater(() -> {
                    editView.setDefaultCloseOperation(DetailContractCustomer.DISPOSE_ON_CLOSE);
                    editView.setVisible(true);
            });
                    editView.setBtnUpdate(new UpdateClick());
        }
  }
  class UpdateClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean result = DAO.UpdateContract(editView.Update());
            if(result) {     
                roomDAO.updateStatusRoom(list.get(view.getSelectionIndex()).getRoomID(),"ĐÃ THUÊ");
                JOptionPane.showMessageDialog(editView, "Cập nhật thành công");
            }
            else 
                JOptionPane.showMessageDialog(editView, "Cập nhật thất bại, vui lòng thử lại sau");
            editView.dispose();
        }
  }
}
