package com.solvd.atm.service;

import com.solvd.atm.domain.Account;

public interface AccountService {

    Account create(Account account);

    Account read(Long id);

    void update(Account account);

    void deleteById(Long deleteId);

}