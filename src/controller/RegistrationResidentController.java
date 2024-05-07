/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Customer;
import view.ResidentRegistration;

/**
 *
 * @author Admin
 */
public class RegistrationResidentController {
    private final ResidentRegistration view;
    private final Customer model;
    private final CustomerDAO DAO;
    public RegistrationResidentController(ResidentRegistration view,Customer model){
        this.view = view;
        this.model = model;
        DAO = new CustomerDAO();
        view.setActionListenerForUpdateBtn(new ClickUpdate());
        RenderData();
    }
    private void RenderData(){
         if (model == null) return;
         view.RenderData(DAO.getResidentDetail(model.getCCCD()));
    }
    public ResidentRegistration Render(){
        return this.view;
    }   
    class ClickUpdate implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DAO.UpdateResident(view.UpdateData()) == true){
                JOptionPane.showMessageDialog(view, "Bạn đã cập Nhật thành công");
            }else {
                JOptionPane.showMessageDialog(view, "Cập nhật thất bại, vui lòng kiểm tra lại thông tin");
            }
        }
    }
}
