package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.Savings;
import com.ironhack.bankingsystem.repository.AccountRepository;
import com.ironhack.bankingsystem.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

}
