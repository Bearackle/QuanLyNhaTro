/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import model.Customer;
import model.User;
import view.SearchMatch;

/**
 *
 * @author Admin
 */
public class SearchMatchController {
    private final AccountDAO userDAO;
    private User user;
    private Customer customer;
    private RoomDAO roomDAO;
    private final SearchMatch searchMatch;
    public SearchMatchController(SearchMatch view, User user,Customer customer)
    {
        this.searchMatch = view;
        this.user = user;
        this.customer = customer;
        roomDAO = new RoomDAO();
        userDAO = new AccountDAO();
        searchMatch.setActionForSearchingButton( new ClickSearching());
        searchMatch.setActionListenerForReportbtn(new ReportClick());
        searchMatch.setToggleBtnListener(new ClickItem());
    }
    public User SearchForUser(String phone)
    {
        return userDAO.getUserByPhoneNumber(phone);
    }
    public SearchMatch Renderer()
    {
        return searchMatch;
    }
    
    //Listener
    class ClickSearching implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchMatch.setDataInfoSearch(SearchForUser(searchMatch.getPhoneNumber()));
            searchMatch.initInfoSearch(true);
        }   
    }
    class ReportClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //send notify to admin
            JOptionPane.showMessageDialog(searchMatch, "Cảm ơn bạn đã báo cáo, Chúng tôi sẽ xem xét báo cáo của bạn");
        }
    }
    class ClickItem implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
                if (customer == null){
                    JOptionPane.showMessageDialog(searchMatch, "Bạn cần thuê phòng trước đã");
                }else 
                {
                int state = e.getStateChange();
                boolean UpdatePolicy = roomDAO.UpdatePolicy(customer.getCCCD(),state == ItemEvent.SELECTED ? "CÓ" : "KHÔNG");
                if(UpdatePolicy == false) {
                    JOptionPane.showConfirmDialog(searchMatch, "Cập nhật không thành công, vui lòng thử lại");
                    return;
                }
                if (state ==  ItemEvent.SELECTED){
                    searchMatch.setToggleBtnState(true);
                }
                else
                    searchMatch.setToggleBtnState(false);
                }
        } 
    }
}
