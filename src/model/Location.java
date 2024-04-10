/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
   public class Location {
    private String Cty;
    private String District;
    private String Ward;
    private String Street;

    public String getCty() {
        return Cty;
    }

    public void setCty(String Cty) {
        this.Cty = Cty;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String Ward) {
        this.Ward = Ward;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }  

        @Override
        public String toString() {
            return "%s, %s, %s, %s".formatted(Street,Ward,District,Cty);
        }

        public Location( String Street, String Ward, String District, String Cty) {
            this.Cty = Cty;
            this.District = District;
            this.Ward = Ward;
            this.Street = Street;
        }
    
    }