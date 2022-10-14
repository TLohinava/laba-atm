package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Bank;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.BankRepository;
import com.solvd.atm.persistence.impl.BankMapperImpl;
import com.solvd.atm.service.BankService;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl() {
        this.bankRepository = new BankMapperImpl();
    }

    @Override
    public Bank create(Bank bank) {
        bank.setId(null);
        bankRepository.create(bank);
        return bank;
    }

    @Override
    public Bank read(Long id) {
        return bankRepository.read(id)
                .orElseThrow(() -> new QueryException("No banks found"));
    }

    @Override
    public void update(Bank bank) {
        bankRepository.update(bank);
    }

    @Override
    public void deleteById(Long deleteId) {
        bankRepository.deleteById(deleteId);
    }
}
