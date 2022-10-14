package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Account;
import com.solvd.atm.persistence.AccountRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class AccountMapperImpl implements AccountRepository {


    @Override
    public void create(Account account) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.create(account);
        }
    }

    @Override
    public Optional<Account> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            return accountRepository.read(id);
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.update(account);
        }

    }

    @Override
    public void deleteById(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
            accountRepository.deleteById(deleteId);
        }
    }
}
