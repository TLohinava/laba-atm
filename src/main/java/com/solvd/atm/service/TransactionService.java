package com.solvd.atm.service;

import com.solvd.atm.domain.Transaction;

public interface TransactionService {

    Transaction create(Long atmId, Long cardId, Transaction transaction);

}