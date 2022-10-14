package com.solvd.atm.service;

import com.solvd.atm.domain.Card;

import java.util.Optional;

public interface CardService {

    Card create(Long accountId, Card card);

    Optional<Card> read(Long id);

    void update(Card card);

    void deleteById(Long deleteId);

}
