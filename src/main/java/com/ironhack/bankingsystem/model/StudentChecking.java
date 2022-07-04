package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name="student_checking_id")
public class StudentChecking extends Account{

    @Enumerated(EnumType.STRING)
    private Status status;
    private String secretKey;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                           BigDecimal penaltyFee, Date creationDate, Status status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.status = status;
        this.secretKey = secretKey;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
