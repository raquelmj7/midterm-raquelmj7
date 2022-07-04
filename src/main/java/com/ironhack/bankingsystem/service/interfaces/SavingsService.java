package com.ironhack.bankingsystem.service.interfaces;

import com.ironhack.bankingsystem.model.Savings;

import java.util.List;

public interface SavingsService {
    Savings store(Savings savings);
    void update(Long id,Savings savings);
    void delete (Long id);
}
