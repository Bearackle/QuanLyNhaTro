/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.BillDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Bill;
import model.Customer;
import model.LandLord;
import model.User;
import view.Bill.BillDetailPanel;
import view.Bill.BillPanel;

/**
 *
 * @author Admin
 */
public class BillController {
    private final BillPanel view;
    private final BillDAO DAO;
    private final Customer model;
    private ArrayList<Bill> list;
    private BillDetailPanel detailPanel;
    public BillController(BillPanel view,Customer customer){
        this.view = view;
        this.model = customer;
        this.DAO = new BillDAO();
        initDataCustomer();
        view.setActionListenerForDetailbtn(new ClickDetailButton());
    }
    private void initDataCustomer(){
        if (model != null){
        list = DAO.getAllBillbyCustomerID(model.getCCCD());
        view.initTable(list);
        }
    }
    public BillPanel Render(){
        return view;
    }
    class ClickDetailButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {          
            detailPanel = new BillDetailPanel();
            detailPanel.initDataCustomer(DAO.getBillCustomerByID(list.get(view.getSelectedItemTable()).getID()));
           java.awt.EventQueue.invokeLater(() -> {
               detailPanel.setDefaultCloseOperation(BillDetailPanel.DISPOSE_ON_CLOSE);
               detailPanel.setVisible(true);
            });
        }
    }
}
