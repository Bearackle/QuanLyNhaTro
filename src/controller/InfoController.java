/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
import java.util.ArrayList;
import java.util.List;
import model.Contract;
import model.Customer;
import model.Room;
import model.User;
import view.Info;

/**
 *
 * @author Admin
 */
public class InfoController {
    private Info info;
    private final ContractDAO contractDAO;
    private final RoomDAO roomDAO;
    private final CustomerDAO customerDAO;
    private Customer customer;
    private User user;
    public InfoController(Info info,User user)
    {
        customerDAO = new CustomerDAO();
        contractDAO = new ContractDAO();
        roomDAO = new RoomDAO();
        this.info = info;
        this.user = user;
        RenderInformation();
    }
    public void RenderInformation()
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
        List<Contract> contracts;
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
}
