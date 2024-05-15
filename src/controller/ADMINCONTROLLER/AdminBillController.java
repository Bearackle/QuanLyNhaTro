/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.BillDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Bill;
import model.BillCustomerDetail;
import model.BillLandlordDetail;
import view.admin.AdminBill;
import view.admin.LandlordBillView;

/**
 *
 * @author Admin
 */
public class AdminBillController {
    private BillDAO DAO;
    private AdminBill view;
    private ArrayList<Bill> bills;
    private LandlordBillView billView;
    public AdminBillController(AdminBill view){
        this.view = view;
        DAO = new BillDAO();
        initData();
        view.setActionListenerFortablebtn(new ClickButton());
        view.setbtnCreate(new ClickCreate());
    }
    public AdminBill Render(){
        return view;
    }
    private void initData(){
          bills = DAO.getAllBillNoMatter();
          view.initTable(bills);
    }
    class ClickButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectionIndex();
            if(bills.get(index) instanceof BillCustomerDetail){
                // duyet 
               boolean result =  DAO.updateBillStatus("ĐÃ THANH TOÁN",bills.get(index).getID());
                if(result){
                 JOptionPane.showMessageDialog(view, "DUYỆT THÀNH CÔNG");
             } else JOptionPane.showMessageDialog(view, "DUYỆT THẤT BẠI, VUI LÒNG THỬ LẠI SAU!!");
            }
            else if(bills.get(index) instanceof BillLandlordDetail billLandlordDetail){
                 billView = new LandlordBillView();
                 billView.initData(DAO.getLandlordInfo(billLandlordDetail.getContract_ID()),bills.get(index).getPrice());
                 java.awt.EventQueue.invokeLater(() -> {
                    billView.setDefaultCloseOperation(LandlordBillView.DISPOSE_ON_CLOSE);
                    billView.setVisible(true);
            });
                 billView.setBtnAccept(new ClickAccept());
            }
        }  
    }
    class ClickAccept implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             boolean result = DAO.updateBillStatus("ĐÃ THANH TOÁN", bills.get(view.getSelectionIndex()).getID());
             if(result){
                 JOptionPane.showMessageDialog(view, "DUYỆT THÀNH CÔNG");
             } else JOptionPane.showMessageDialog(view, "DUYỆT THẤT BẠI, VUI LÒNG THỬ LẠI SAU!!");
             billView.dispose();
        }
    }
    class ClickCreate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {  
             Bill bill = view.getDataBill();
             if (bill.getCustomer_id() == 0) JOptionPane.showMessageDialog(view, "Vui lòng điền thông tin đầy đủ");
             if (bill instanceof BillCustomerDetail billcus){
                 int fee = DAO.CreateCustomerBill(billcus);
                 if (fee != 0) {
                     JOptionPane.showMessageDialog(view, "Tạo thành công Hóa đơn!");
                     DAO.CreateNewBillLandlord(billcus,fee);
                     initData();
                 }
                 else JOptionPane.showMessageDialog(view, "Tạo Hóa đơn thất bại!");
             }
             else {
                 
             }
        }
        
    }
}
