package com.ironhack.bankingsystem.classes;
import javax.persistence.Embeddable;

@Embeddable
public class PrimaryAddress {
    private String street;
    private String city;

    public PrimaryAddress() {
    }

    public PrimaryAddress(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}