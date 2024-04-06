/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import DAO.RoomDAO;
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
    private ContractDAO contractDAO;
    private RoomDAO roomDAO;
    private CustomerDAO customerDAO;
    private Customer customer;
    private User user;
    public InfoController(Info info,User user)
    {
        customerDAO = new CustomerDAO();
        contractDAO = new ContractDAO();
        roomDAO = new RoomDAO();
        this.info = info;
    }
    public void RenderInformation()
    {
        
    }
    public void RenderContractList()
    {
        
    }
    public Room getRoomByContract(int id)
    {
        return roomDAO.getRoomInfoWithId(id);  
    }
}
