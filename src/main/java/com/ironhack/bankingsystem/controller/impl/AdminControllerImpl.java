package com.ironhack.bankingsystem.controller.impl;

import com.ironhack.bankingsystem.controller.interfaces.AdminController;
import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.Savings;
import com.ironhack.bankingsystem.repository.SavingsRepository;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import com.ironhack.bankingsystem.service.impl.AccountServiceImpl;
import com.ironhack.bankingsystem.service.impl.SavingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminControllerImpl implements AdminController {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Autowired
    private SavingsServiceImpl savingsServiceImpl;

    @Autowired
    private SavingsRepository savingsRepository;

    @GetMapping ("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAllAccounts() {
        return accountServiceImpl.findAllAccounts();
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings store(@RequestBody @Valid Savings savings) {
        return savingsRepository.save(savings);
    }

    @PutMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody
    @Valid Savings savings) {
        Optional<Savings> savingsOptional= savingsRepository.findById(id);
        if (!savingsOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Savings Account not found.");
        }
        savings.setId(id);
        savingsRepository.save(savings);
    }

    @DeleteMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        savingsServiceImpl.delete(id);
    }
}
