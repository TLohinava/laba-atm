package com.solvd.atm.persistence;

import com.solvd.atm.domain.Account;

import java.util.Optional;

public interface AccountRepository {

    void create(Account account);

    Optional<Account> read(Long id);

    void update(Account account);

    void deleteById(Long deleteId);

}