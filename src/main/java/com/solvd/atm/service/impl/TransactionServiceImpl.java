package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Transaction;
import com.solvd.atm.persistence.TransactionRepository;
import com.solvd.atm.persistence.impl.TransactionMapperImpl;
import com.solvd.atm.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl() {
        this.transactionRepository = new TransactionMapperImpl();
    }

    @Override
    public Transaction create(Long atmId, Long cardId, Transaction transaction) {
        transaction.setId(null);
        transactionRepository.create(atmId, cardId, transaction);
        return transaction;
    }

    @Override
    public boolean read(Long cardId) {
        return transactionRepository.read(cardId);
    }
}