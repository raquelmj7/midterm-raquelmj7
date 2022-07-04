package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.model.Account;
import com.ironhack.bankingsystem.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long> {
    Optional<Account> findById(long Id);
}
