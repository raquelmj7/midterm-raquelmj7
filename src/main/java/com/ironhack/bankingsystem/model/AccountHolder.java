package com.ironhack.bankingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.bankingsystem.classes.PrimaryAddress;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
 @PrimaryKeyJoinColumn (name="account_holder_id")
public class AccountHolder extends User{
    private Date dateOfBirth;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "street", column = @Column(name = "street"))
    })
    private PrimaryAddress primaryAddress;

     @OneToMany (mappedBy = "primaryOwner")
     @JsonIgnore
     private List<Account> accountPrimaryList;


    @OneToMany (mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account>accountSecondaryList;

    public AccountHolder() {
    }

    public AccountHolder(String username, String password, Date dateOfBirth, PrimaryAddress primaryAddress) {
        super(username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PrimaryAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(PrimaryAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }
}

