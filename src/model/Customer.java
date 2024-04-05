/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Customer {
    private int CCCD;
    private String Name;
    private String Phone;
    private String Gender;
    private String Birthday;
    private int Contract_id;
    private String BankAccount;
    private String RelativeName;
    private String RelativeNumber;
    private String Enter_date;

    public int getCCCD() {
        return CCCD;
    }

    public void setCCCD(int CCCD) {
        this.CCCD = CCCD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public int getContract_id() {
        return Contract_id;
    }

    public void setContract_id(int Contract_id) {
        this.Contract_id = Contract_id;
    }

    public String getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(String BankAccount) {
        this.BankAccount = BankAccount;
    }

    public String getRelativeName() {
        return RelativeName;
    }

    public void setRelativeName(String RelativeName) {
        this.RelativeName = RelativeName;
    }

    public String getRelativeNumber() {
        return RelativeNumber;
    }

    public void setRelativeNumber(String RelativeNumber) {
        this.RelativeNumber = RelativeNumber;
    }

    public String getEnter_date() {
        return Enter_date;
    }

    public void setEnter_date(String Enter_date) {
        this.Enter_date = Enter_date;
    }
}
