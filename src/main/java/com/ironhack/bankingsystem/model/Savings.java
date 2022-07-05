package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Entity
@PrimaryKeyJoinColumn(name="savings_id")
public class Savings extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column=@Column(name="amount_minimum_balance")),
            @AttributeOverride(name="currency", column=@Column(name="currency_minimum_balance")),
            @AttributeOverride(name="rounding", column=@Column(name="rounding_minimum_balance"))
    })
    @Max(1000)
    @Min(100)
    private Money minimumBalance;

    @Max(0)
    @DecimalMax("50")
    private BigDecimal interestRate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String secretKey;


    //empty constructor
    public Savings() {
    }

    //constructor with the default interestRate and minimumBalance
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                   Date creationDate, Status status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.minimumBalance = new Money(new BigDecimal(1000));
        this.interestRate = new BigDecimal(0.025);
        this.status = status;
        this.secretKey = secretKey;
    }

    //constructor with only the default interestRate
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                   Date creationDate, Money minimumBalance, Status status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = new BigDecimal(0.025);
        this.status = status;
        this.secretKey = secretKey;
    }

    //constructor with only the default minimumBalance
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                   Date creationDate, BigDecimal interestRate, Status status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.minimumBalance = new Money(new BigDecimal(1000));
        this.interestRate = interestRate;
        this.status = status;
        this.secretKey = secretKey;
    }

    //full constructor
    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                   Date creationDate, Money minimumBalance, BigDecimal interestRate, Status status, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = status;
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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

    private String updateInterestSavings(){

        Date actualDate= new Date(String.valueOf(LocalDate.now()));
        Date lastChangeDate=actualDate;

        Date actualYear = new Date (actualDate.getYear());         //To check the year
        Date lastChangeYear = new Date (lastChangeDate.getYear());
        int resp0= lastChangeYear.compareTo(actualYear);


        if(resp0==0) {        //not even a year has passed since the last increase
            return "Your interest rate has not increased.";

        }else if((resp0==-1 || resp0==1)) {    //one year or more has passed since the last increase

                System.out.println("Your interest rate has increased by 1%.");     //Apply interest
                BigDecimal interest = new BigDecimal("0.01");
                setInterestRate(interestRate.add(interest));
                BigDecimal newAmount2 = new BigDecimal(String.valueOf(getBalance().getAmount().multiply(getInterestRate())));
                getBalance().increaseAmount(newAmount2);
                lastChangeDate = actualDate;
            }
        return "Applied interest";
    }
}
