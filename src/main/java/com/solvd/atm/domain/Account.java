package com.solvd.atm.domain;

import java.math.BigInteger;
import java.util.List;

public class Account {

    private BigInteger accountNumber;
    private List<Card> cards;

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}