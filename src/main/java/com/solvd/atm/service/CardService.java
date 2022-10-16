package com.solvd.atm.service;

import com.solvd.atm.domain.Card;

public interface CardService {

    Card create(Long accountId, Card card);

    Card read(Long id);

    void update(Card card);

    void deleteById(Long deleteId);

}