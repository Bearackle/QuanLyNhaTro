/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Contract;
import model.Customer;
import model.Room;
import model.User;
import view.Room.ReviewRoomView;
import view.Room.RoomDetail;
import view.Room.TimePicker2;

/**
 *
 * @author Admin
 */
public class RoomDetailController {
    private RoomDetail roomDetail;
    private RoomDAO roomDAO;
    private Room room;
    private TimePicker2 time;
    private User user;
    private Customer customer;
    private ContractDAO contractDAO;
    private final CustomerDAO customerDAO;
    public RoomDetailController(RoomDetail roomDetail){
        this.roomDetail = roomDetail;
        roomDetail.setDefaultCloseOperation(RoomDetail.DISPOSE_ON_CLOSE);
        roomDAO = new RoomDAO();
        customerDAO = new CustomerDAO();
        contractDAO = new ContractDAO();
    }
    public RoomDetailController(Room room,User user)
    {
        this(new RoomDetail());
        this.room = room;
        this.user = user;
        this.customer = customerDAO.getCustomer(user.getPhone());
        roomDetail.initImage(roomDAO.getRoomImages(room.getID()));
        roomDetail.initData(this.room);
        roomDetail.setListenerForbtnThue(new ClickBtnThue());
        roomDetail.setActionListenerBtnShowReviwe(new OpenReview());
    }
    class ClickBtnThue implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(customer == null){
                JOptionPane.showMessageDialog(roomDetail, "Bạn cần đăng ký thông tin trước khách hàng trước!!");
                return;
            }
            time = new TimePicker2();
            time.setActionListenerForOK(new ClickOK());
            java.awt.EventQueue.invokeLater(() -> {
                    time.setDefaultCloseOperation(TimePicker2.DISPOSE_ON_CLOSE);
                    time.setVisible(true);
            });
        }
    }
    class ClickOK implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                Contract contract = new Contract();
                contract.setCustomerCCCD(customer.getCCCD());
                contract.setCancelDate(time.getSelectedDate().plusMonths(time.getMonth()));
                contract.setCustomerName(customer.getName());
                contract.setDeposit(room.getPrices());
                contract.setDuration(time.getMonth());
                contract.setElecticPrice(3500);
                contract.setWaterPrice(6000);
                contract.setEnterDate(time.getSelectedDate());
                contract.setNumberOfPeople(1);
                contract.setPrice(room.getPrices());
                contract.setRoomID(room.getID());
                contract.setSigned_date(time.getSelectedDate());
                contract.setStatus("CHỜ DUYỆT");
                boolean check = contractDAO.CreateNewCustomerContract(contract);
                boolean check2 = roomDAO.updateStatusRoom(room.getID(), "CHỜ DUYỆT");
            if (check == true && check2 == true){
                JOptionPane.showMessageDialog(roomDetail, "Bạn đã Đăng ký thành công, hẹn gặp lại bạn vào ngày đã hẹn!!");
            }
                time.dispose();
        }
    }
    class OpenReview implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ReviewRoomView reviewRoomView = new ReviewRoomView();
            reviewRoomView.initList(roomDAO.getAllReviewRoomByRoomId(room.getID()));
            java.awt.EventQueue.invokeLater(()->{
                reviewRoomView.setDefaultCloseOperation(ReviewRoomView.DISPOSE_ON_CLOSE);
                reviewRoomView.setVisible(true);
            });
        }
    }
}
