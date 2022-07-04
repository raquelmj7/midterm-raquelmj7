package com.ironhack.bankingsystem.repository;

import com.ironhack.bankingsystem.model.CreditCard;
import com.ironhack.bankingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository <CreditCard,Long> {

    Optional<CreditCard> findById(long Id);
}
