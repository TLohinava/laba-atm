package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Account;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.AccountRepository;
import com.solvd.atm.persistence.impl.AccountMapperImpl;
import com.solvd.atm.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = new AccountMapperImpl();
    }

    @Override
    public Account create(Account account) {
        account.setId(null);
        accountRepository.create(account);
        return account;
    }

    @Override
    public Account read(Long id) {
        return accountRepository.read(id)
                .orElseThrow(() -> new QueryException("No accounts found"));
    }

    @Override
    public void update(Account account) {
        accountRepository.update(account);
    }

    @Override
    public void deleteById(Long deleteId) {
        accountRepository.deleteById(deleteId);
    }
}
