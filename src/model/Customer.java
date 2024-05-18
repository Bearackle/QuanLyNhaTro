/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Customer extends User{
    private Long CCCD;
    private String Name;
    private String Phone;
    private String Gender;
    private LocalDate Birthday;
    private int ContractId;
    private String BankAccount;
    private String RelativeName;
    private String RelativeNumber;
    private String EnterDate;
    private int RoomIdMatch;
    public Long getCCCD() {
        return CCCD;
    }

    public void setCCCD(Long CCCD) {
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

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate Birthday) {
        this.Birthday = Birthday;
    }

    public int getContractId() {
        return ContractId;
    }

    public void setContractId(int Contract_id) {
        this.ContractId = Contract_id;
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

    public String getEnterDate() {
        return EnterDate;
    }

    public void setEnterDate(String EnterDate) {
        this.EnterDate = EnterDate;
    }

    public int getRoomIdMatch() {
        return RoomIdMatch;
    }

    public void setRoomIdMatch(int RoomIdMatch) {
        this.RoomIdMatch = RoomIdMatch;
    }
}
