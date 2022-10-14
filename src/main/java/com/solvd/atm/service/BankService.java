package com.solvd.atm.service;

import com.solvd.atm.domain.Bank;

import java.util.Optional;

public interface BankService {

    Bank create(Bank bank);

    Optional<Bank> read(Long id);

    void update(Bank bank);

    void deleteById(Long deleteId);

}
