/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.ADMINCONTROLLER;

import DAO.CustomerDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Report;
import view.admin.ReportCustomerView;

/**
 *
 * @author Admin
 */
public class ReportController {
    private CustomerDAO DAO;
    private ReportCustomerView view;
    private ArrayList<Report> listOfReport;
    public ReportController(ReportCustomerView view){
        this.view = view;
        DAO = new CustomerDAO();
        listOfReport = DAO.getAllReport();
        view.setBtnDelete(new ClickDelete());
        initData();
    }
    private void initData(){
        view.initListReport(listOfReport);
    }
    public ReportCustomerView Render(){
        return this.view;
    }
    class ClickDelete implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getSelectedMode()==false){
                view.setJListSelect(new ClickDeleteItem());
                view.setbtnValue("Chọn Report");
                view.setSelectedMode(true);
            }
            else {
                view.setRefuseSelect();
                view.setbtnValue("Xóa");
                view.setSelectedMode(false);
            }
        }
    }
    class ClickDeleteItem extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            if(DAO.UpdateSolveReport(listOfReport.get(view.getSelectedItem()))){
                JOptionPane.showMessageDialog(view, "Xóa thành công");
            }
            
        }
    }
}
