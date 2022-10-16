package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Transaction;
import com.solvd.atm.persistence.MyBatisConfig;
import com.solvd.atm.persistence.TransactionRepository;
import org.apache.ibatis.session.SqlSession;

public class TransactionMapperImpl implements TransactionRepository {

    @Override
    public void create(Long atmId, Long cardId, Transaction transaction) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            TransactionRepository transactionRepository = sqlSession.getMapper(TransactionRepository.class);
            transactionRepository.create(atmId, cardId, transaction);
        }
    }
}
