/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import DAO.LandLordDAO;
import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contract;
import model.Customer;
import model.Room;
import model.User;
import view.User.Info;
import view.User.NewCustomer;

/**
 *
 * @author Admin
 */
public class InfoController {
    private final Info info;
    private  final ContractDAO contractDAO;
    private final RoomDAO roomDAO;
    private final CustomerDAO customerDAO;
    private Customer customer;
    private final User user;
    private NewCustomer newCustomer;
    private final LandLordDAO landLordDAO;
    private List<Contract> contracts;
    public InfoController(Info info,User user)
    {
        customerDAO = new CustomerDAO();
        contractDAO = new ContractDAO();
        roomDAO = new RoomDAO();
        landLordDAO = new LandLordDAO();
        this.info = info;
        this.user = user;
        RenderInformation();
        info.setUpdateBtn(new clickUpdateBtn());
        info.setBecomeCustomerBtn(new clickNewCustomer());
        info.setActionListenerBtnlandlord(new clickLandlord());
        info.setActionListenerDeleteContact(new ClickDelete());
    }
    private void RenderInformation()
    {
        customer = customerDAO.getCustomer(user.getPhone());
        if (customer!=null)
        {
        info.setCustomerInfo(customer);
        RenderContractList();
        }
        else 
        {
           info.setUserInfo(user);
        }
    }
 
    public void RenderContractList()
    {
        contracts = contractDAO.getAllContractByCustomer(customer.getCCCD());
        info.initContract(contracts);
    }
    public Info Render()
    {
        return info;
    }
    public Room getRoomByContract(int id)
    {
        return roomDAO.getRoomInfoWithId(id);  
    }
    class clickUpdateBtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             
        }
    }
    class clickNewCustomer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            newCustomer = new NewCustomer(user);
            newCustomer.setDefaultCloseOperation(NewCustomer.DISPOSE_ON_CLOSE);
            newCustomer.setVisible(true);
            newCustomer.setActionListenerForBtn((ActionEvent e1) -> {
                customerDAO.CreeateNewCustomer(newCustomer.CreateCustomer());
                newCustomer.dispose();
                JOptionPane.showMessageDialog(info,"Đã Đăng ký khách hàng thành công!!");
            });
        }     
    }
    class clickLandlord implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            newCustomer = new NewCustomer(user);
            newCustomer.setDefaultCloseOperation(NewCustomer.DISPOSE_ON_CLOSE);
            newCustomer.setVisible(true);
            newCustomer.setLandLordRegisteriView();
            newCustomer.setActionListenerForBtn((ActionEvent e1) -> {
                boolean createLandlord = landLordDAO.CreateNewLandLord(newCustomer.CreateLandLord());
                if (createLandlord == true) JOptionPane.showMessageDialog(newCustomer, "Đã đăng ký thành công, cảm ơn bạn!");
                else JOptionPane.showMessageDialog(newCustomer, "Đăng ký thất bại, vui lòng thử lại sau");
                newCustomer.dispose();
                JOptionPane.showMessageDialog(info,"Đã Đăng ký khách hàng thành công!!");
            });
        }     
    }
    class ClickDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (info.getSelectedMode()==false){
                info.setJListSelect(new ClickDeleteItem());
                info.setbtnValue("Chọn phòng");
                info.setSelectedMode(true);
            }
            else {
                info.setRefuseSelect();
                info.setbtnValue("Trả Phòng");
                info.setSelectedMode(false);
            }
        }
        
    }
    class ClickDeleteItem extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
               contractDAO.updateStatusContractCustomer(contracts.get(info.getSelectedItem()).getID(), "XÓA");
        }
    }
}
