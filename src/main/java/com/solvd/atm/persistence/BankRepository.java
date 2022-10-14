package com.solvd.atm.persistence;

import com.solvd.atm.domain.Bank;
import com.solvd.atm.domain.Client;

import java.util.Optional;

public interface BankRepository {

    void create(Bank bank);

    void createClientConnection(Bank bank, Client client);

    Optional<Bank> read(Long id);

    void update(Bank bank);

    void deleteById(Long deleteId);

}
