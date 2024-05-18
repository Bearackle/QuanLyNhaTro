/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Customer;
import model.User;
import view.CustomControl.toggle.ToggleListener;
import view.User.ReportWindow;
import view.User.SearchMatch;

/**
 *
 * @author Admin
 */
public class SearchMatchController {
    private final AccountDAO userDAO;
    private final CustomerDAO customerDAO;
    private User user;
    private Customer customer;
    private RoomDAO roomDAO;
    private final SearchMatch searchMatch;
    private ArrayList<Customer> Roommates;
    private ReportWindow rpw;
    public SearchMatchController(SearchMatch view, User user,Customer customer)
    {
        this.searchMatch = view;
        this.user = user;
        this.customer = customer;
        roomDAO = new RoomDAO();
        userDAO = new AccountDAO();
        customerDAO = new CustomerDAO();
        searchMatch.setActionForSearchingButton( new ClickSearching());
        searchMatch.setActionListenerForReportbtn(new ReportClick());
        searchMatch.setActionListenerForAddButton(new ClickAddBtn());
        searchMatch.setToggleBtnListener(new ClickPolicy());
        searchMatch.setBtnReport(new ClickReport());
        initDataTable();
        initPolicy();
    }
    public User SearchForUser(String phone)
    {
        return userDAO.getUserByPhoneNumber(phone);
    }
    public SearchMatch Renderer()
    {
        return searchMatch;
    }
    private void initDataTable(){
         Roommates = customerDAO.getAllRoommate(roomDAO.getDataRoomWithCustomerID(customer.getCCCD()).getID());
         searchMatch.initTable(Roommates);
    }
    private void initPolicy(){
        searchMatch.setToggleBtnState(roomDAO.getRoomIsAllowMatch
        (roomDAO.getDataRoomWithCustomerID(customer.getCCCD()).getID()));
    }
    //Listener
    class ClickSearching implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = SearchForUser(searchMatch.getPhoneNumber());
            if(user != null){
                searchMatch.setDataInfoSearch(user);
            }
            else{
                searchMatch.SetEmptyContent("Không tìm thấy người dùng, vui lòng nhập lại thông tin");
            }
        }   
    }
    class ReportClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //send notify to admin
            JOptionPane.showMessageDialog(searchMatch, "Cảm ơn bạn đã báo cáo, Chúng tôi sẽ xem xét báo cáo của bạn");
        }
    }
    class ClickPolicy implements ToggleListener{
        @Override
        public void onSelected(boolean selected) {
           if (customer == null){
                    JOptionPane.showMessageDialog(searchMatch, "Bạn cần thuê phòng trước!!");
                }else 
                {
                boolean UpdatePolicy = roomDAO.UpdatePolicy(customer.getCCCD(),selected ? "CÓ" : "KHÔNG");
                if(UpdatePolicy == false) {
                    JOptionPane.showConfirmDialog(searchMatch, "Cập nhật không thành công, vui lòng thử lại");
                    }
                }
        }
        @Override
        public void onAnimated(float animated) {
            
        }
    }
    class ClickAddBtn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (userDAO.isCustomer(searchMatch.getPhoneNumber())){
                if(customerDAO.UpdateRoomIDMatch(searchMatch.getPhoneNumber(),
                        roomDAO.getDataRoomWithCustomerID(customer.getCCCD()).getID())){
                    JOptionPane.showMessageDialog(searchMatch, "Thêm bạn thành cônng!");
                    initDataTable();
                }
                else 
                JOptionPane.showMessageDialog(searchMatch, "Không thể thêm bạn cùng phòng, Do đối tượng muốn thêm không phải khách hàng của hệ thống");
            }
             else {
                JOptionPane.showMessageDialog(searchMatch, "Không thể thêm bạn cùng phòng, vui lòng liên hệ admin hoặc thử lại sau!");
            }
            
        }    
    }
    class ClickReport implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             rpw = new ReportWindow();
              java.awt.EventQueue.invokeLater(() -> {
                    rpw.setDefaultCloseOperation(ReportWindow.DISPOSE_ON_CLOSE);
                    rpw.setVisible(true);
            });
             rpw.setBtnSend(new SendReport());
        }
    }
    class SendReport implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
              if (customerDAO.CreateNewReport(rpw.getReport(customer.getCCCD()))){
                  JOptionPane.showMessageDialog(searchMatch, "Gửi Khiếu nại thành công");
              } else {
                  JOptionPane.showMessageDialog(searchMatch, "Gửi yêu cầu thất bại, bạn vui lòng kiểm tra mã phòng của đối tượng có chính xác hay không?");
              }
              rpw.dispose();
        }
    }
}
