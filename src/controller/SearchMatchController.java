/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
    private final SearchMatch searchMatch;
    public SearchMatchController(SearchMatch view, User user)
    {
        this.searchMatch = view;
        this.user = user;
        userDAO = new AccountDAO();
        searchMatch.setActionForSearchingButton( new ClickSearching());
        searchMatch.setActionListenerForReportbtn(new ReportClick());
        
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
}
