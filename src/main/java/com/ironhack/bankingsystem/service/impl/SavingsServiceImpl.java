package com.ironhack.bankingsystem.service.impl;

import com.ironhack.bankingsystem.model.Savings;
import com.ironhack.bankingsystem.repository.SavingsRepository;
import com.ironhack.bankingsystem.service.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    @Override
    public Savings store(Savings savings) {
        return null;
    }

    @Override
    public void update(Long id, Savings savings) {
        Optional<Savings> savingsOptional= savingsRepository.findById(id);
        if (!savingsOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Savings Account not found.");
        }
        savings.setId(id);
        savingsRepository.save(savings);

    }

    @Override
    public void delete(Long id) {
        Savings savings = savingsRepository.findById(id).orElseThrow
                (() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Savings Account not found :C"));

        savingsRepository.deleteById(id);
    }


}
