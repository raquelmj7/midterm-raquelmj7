package com.ironhack.bankingsystem.service.interfaces;

import com.ironhack.bankingsystem.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAllAccounts();
}
