package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Account;
import com.solvd.atm.domain.Card;
import com.solvd.atm.persistence.AccountRepository;
import com.solvd.atm.persistence.impl.AccountMapperImpl;
import com.solvd.atm.service.AccountService;
import com.solvd.atm.service.CardService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CardService cardService;

    public AccountServiceImpl() {
        this.accountRepository = new AccountMapperImpl();
        this.cardService = new CardServiceImpl();
    }

    @Override
    public Account create(Account account) {
        account.setId(null);
        accountRepository.create(account);

//        if (account.getCards() != null) {
//            List<Card> cards = account.getCards().stream()
//                    .map(card -> cardService.create(account.getId(), card))
//                    .collect(Collectors.toList());
//            account.setCards(cards);
//        }
        return account;
    }

    @Override
    public Optional<Account> read(Long id) {
        return accountRepository.read(id);
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
