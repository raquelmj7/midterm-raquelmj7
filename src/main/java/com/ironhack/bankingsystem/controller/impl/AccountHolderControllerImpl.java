package com.ironhack.bankingsystem.controller.impl;

import com.ironhack.bankingsystem.classes.Money;
import com.ironhack.bankingsystem.controller.interfaces.AccountHolderController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Override
    @GetMapping("/balances")
    @ResponseStatus(HttpStatus.OK)
    public Money accountBalance() {

        return null;
    }
}
