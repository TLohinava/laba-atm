package com.solvd.atm.service;

import com.solvd.atm.domain.Bank;

public interface BankService {

    Bank create(Bank bank);

    Bank read(Long id);

    void update(Bank bank);

    void deleteById(Long deleteId);

}
