package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name="credit_card_id")
public class CreditCard extends Account{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column=@Column(name="amount_credit_limit")),
            @AttributeOverride(name="currency", column=@Column(name="currency_credit_limit")),
            @AttributeOverride(name="rounding", column=@Column(name="rounding_credit_limit"))
    })
    @Min(100)
    @Max(100000)
    private Money creditLimit;
    @Min(0)
    @DecimalMin("1")
    @Max(0)
    @DecimalMax("2")
    private BigDecimal interestRate;

    public CreditCard() {
    }

    //constructor with the default creditLimit and interestRate
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                      Date creationDate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.creditLimit= new Money (new BigDecimal(100));
        this.interestRate=new BigDecimal(0.2);
    }

    //constructor with only the default interestRate
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                      Date creationDate, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.creditLimit = creditLimit;
        this.interestRate=new BigDecimal(0.2);
    }

    //constructor with only the default creditLimit
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                      Date creationDate, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.creditLimit= new Money (new BigDecimal(100));
        this.interestRate = interestRate;
    }

    //full constructor
    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee,
                      Date creationDate, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, penaltyFee, creationDate);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    @Override                      //add interest
    public Money getBalance() {
        return super.getBalance();
    }


    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {

        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }


    private void updateInterestCreditCard(){

        Date actualDate= new Date(String.valueOf(LocalDate.now()));
        Date lastChangeDate = getCreationDate();                  //aqui no se guarda la actual, PREGUNTAR

        Date actualYear = new Date (actualDate.getYear());         //To check the year
        Date lastChangeYear = new Date (lastChangeDate.getYear());
        int resp0= lastChangeYear.compareTo(actualYear);

        Date actualMonth = new Date (actualDate.getMonth());       //To check the month
        Date lastChangeMonth = new Date (lastChangeDate.getMonth());
        int resp1= lastChangeMonth.compareTo(actualMonth);      //o la fecha de creacion

        BigDecimal actualInterestRate =new BigDecimal(String.valueOf(getInterestRate()));
        BigDecimal number= new BigDecimal("0.12");
        int resp2= number.compareTo(actualInterestRate);

        if((resp1 ==0) && resp0==0) {        //not even a month has passed since the last increase (same month and year)
            System.out.println("Your interest rate has not increased.");

        }else if((resp1==-1 || resp1==1) || (resp1 ==0 && resp0==-1)) {    //one month has passed since the last increase
            if (resp2 == 1) {                                                           //or same month but different year
                System.out.println("Your interest rate has not increased.");

            } else if (resp2 == -1 || resp2 == 0) {    //interest rate >= 12%
                BigDecimal interest = new BigDecimal("0.01");
                System.out.println("Your interest rate has increased by 1%.");
                setInterestRate(interestRate.add(interest));   //COMPROBAR
                BigDecimal newAmount = new BigDecimal(String.valueOf(getBalance().getAmount().multiply(getInterestRate())));
                getBalance().increaseAmount(newAmount);
            }
        }
    }
}
