package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column=@Column(name="amount_balance")),
            @AttributeOverride(name="currency", column=@Column(name="currency_balance")),
            @AttributeOverride(name="rounding", column=@Column(name="rounding_balance"))
    })

    private Money balance;

    @ManyToOne
    @JoinColumn(name="primary_owner")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name="secondary_owner")
    private AccountHolder secondaryOwner;

    private BigDecimal penaltyFee;

    private Date creationDate;

    @Autowired
    private AccountRepository accountRepository;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Date creationDate) {
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.creationDate=creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    private String transferMoney(AccountHolder owner, long id, BigDecimal amountToTransfer){
        BigDecimal currentBalance = getBalance().getAmount();
        BigDecimal zero=new BigDecimal(0);
        int resp =currentBalance.compareTo(zero);
        int resp2 =currentBalance.compareTo(amountToTransfer);

        Optional <Account> account= accountRepository.findById(id);

        if(!account.isPresent()){
            throw new IllegalArgumentException("Account not found.");
        }

        Account actualAccount=account.get();

        if(!(actualAccount.getPrimaryOwner().equals(owner)||actualAccount.getSecondaryOwner().equals(owner))){
            throw new IllegalArgumentException("Owner not found.");
        }

        if (resp==-1 || resp == 0){              //currentBalance<=0
            return "Sorry, you do not have enough funds to make a transfer";

        } else if(resp == 1){                    //currentBalance>0
            if (resp2==-1 || resp == 0){
                throw new IllegalArgumentException("Your funds are less than the amount to be transferred," +
                        "please enter another amount:");

            } else if(resp2 == 1){
                //hacer la transferencia!!!!!!!!!!!!!!
                getBalance().decreaseAmount(amountToTransfer);
                actualAccount.getBalance().increaseAmount(amountToTransfer);
            }
        }
        return "Successful transfer";
    }
}
