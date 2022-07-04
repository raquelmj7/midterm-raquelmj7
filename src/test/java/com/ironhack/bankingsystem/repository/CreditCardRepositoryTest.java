package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.classes.PrimaryAddress;
import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.AccountHolder;
import com.ironhack.bankingsystem.model.CreditCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.nio.channels.AcceptPendingException;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardRepositoryTest {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {}


    @AfterEach
    void tearDown() {
    }

    void findById_checkingId_checking() {

    }
}
