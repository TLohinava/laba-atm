package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.CardRepository;
import com.solvd.atm.persistence.impl.CardMapperImpl;
import com.solvd.atm.service.CardService;

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl() {
        this.cardRepository = new CardMapperImpl();
    }

    @Override
    public Card create(Long accountId, Card card) {
        card.setId(null);
        cardRepository.create(accountId, card);
        return card;
    }

    @Override
    public Card read(Long id) {
        return cardRepository.read(id)
                .orElseThrow(() -> new QueryException("No cards found"));
    }

    @Override
    public void update(Card card) {
        cardRepository.update(card);
    }

    @Override
    public void deleteById(Long deleteId) {
        cardRepository.deleteById(deleteId);
    }
}
