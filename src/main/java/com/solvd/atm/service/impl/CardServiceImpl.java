package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Card;
import com.solvd.atm.persistence.CardRepository;
import com.solvd.atm.persistence.impl.CardMapperImpl;
import com.solvd.atm.service.CardService;

import java.util.Optional;

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
    public Optional<Card> read(Long id) {
        return cardRepository.read(id);
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
