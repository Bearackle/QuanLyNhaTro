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
      initData("XÓA");
      view.setActionListenerFortablebtn(new ClickAccept(), new ClickDelete(), new ClickEdit());
      view.setTggleBtn(new ShowAll());
  }
  private void initData(String status){
      list = DAO.getAllContracts();
      list.removeIf(con ->  !"".equals(status) ? con.isStatus().equals(status) : false);
      view.initDataTable(list);
  }
  public ContractCustomer render(){
      return view;
  }
  class ClickAccept implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectionIndex();
            if (list.get(index).isStatus().equals("YÊU CẦU XÓA")){
                JOptionPane.showMessageDialog(view, "Vui lòng chọn tùy chọn phù hợp");
                return;
            } else {
              DAO.updateStatusContractCustomer(list.get(view.getSelectionIndex()).getID(),"ĐÃ DUYỆT");
              roomDAO.updateStatusRoom(list.get(index).getRoomID(), "ĐÃ THUÊ");
            }
            JOptionPane.showMessageDialog(view, "Duyệt thành công");
        }
  }
  class ClickDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
               int index = view.getSelectionIndex();
               list.remove(index);
               view.initDataTable(list);
              if(DAO.updateStatusContractCustomer(list.get(index).getID(), "XÓA") &&
               roomDAO.updateStatusRoom(list.get(index).getRoomID(), "TRỐNG")){
                  JOptionPane.showMessageDialog(view, "XÓA THÀNH CÔNG");
              }
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
  class ShowAll implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
           if(e.getStateChange() == ItemEvent.SELECTED){
                 view.setTextTggbtn("Rút gọn");
                 initData("");
           }
           else{
               view.setTextTggbtn("Xem tất cả");
               initData("XÓA");
           }
        }
      
  }
}
