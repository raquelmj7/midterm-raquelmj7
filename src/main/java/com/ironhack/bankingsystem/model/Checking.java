package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name="checking_id")
public class Checking extends Account{

    private BigDecimal monthlyMaintenanceFee;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column=@Column(name="amount_minimum_balance")),
            @AttributeOverride(name="currency", column=@Column(name="currency_minimum_balance")),
            @AttributeOverride(name="rounding", column=@Column(name="rounding_minimum_balance"))
    })
    @Min(250)
    private Money minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String secretKey;

    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                    Date creationDate,Money minimumBalance, Status status, String secretKey) {
        super(balance,primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.monthlyMaintenanceFee = new BigDecimal(12);
        this.minimumBalance = minimumBalance;
        this.status = status;
        this.secretKey = secretKey;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
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
