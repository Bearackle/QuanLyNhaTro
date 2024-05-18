/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.CustomerDAO;
import model.Customer;
import model.User;
import view.Contract.Contract;

/**
 *
 * @author Admin
 */
public class ContractController {
    private Contract contractView;
    private ContractDAO contractDAO;
    private CustomerDAO customerDAO;
    public ContractController(Contract contractView,User user)
    {
        this.contractView = contractView;
        contractDAO = new ContractDAO();
        customerDAO = new CustomerDAO();
        Customer customer = customerDAO.getCustomer(user.getPhone());
        if (customer != null)
        {
            RenderContract(customer);
        }
    }
    public void RenderContract(Customer customer)
    {
        if (contractDAO.getAvailableContractByCustomerID(customer.getCCCD()) == null) {
             return;
        }
           
        contractView.LoadContract(contractDAO.contractDetailCustomer(customer.getCCCD()));
    }
    public void RenderContractNoInfo()
    {
    }
    public Contract RenderForm()
    {
        return this.contractView;
    }
}
