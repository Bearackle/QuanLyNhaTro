/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class LandLord extends User{
     private Long CCCD;
     private String BankAccount;
    public Long getCCCD() {
        return CCCD;
    }

    public void setCCCD(Long CCCD) {
        this.CCCD = CCCD;
    }

    public String getBankAccount() {
        return BankAccount;
    }
    public void setBankAccount(String BankAccount) {
        this.BankAccount = BankAccount;
    }
}
