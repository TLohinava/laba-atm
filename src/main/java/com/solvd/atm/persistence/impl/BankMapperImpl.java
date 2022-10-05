package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Bank;
import com.solvd.atm.domain.Client;
import com.solvd.atm.persistence.BankRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class BankMapperImpl implements BankRepository {

    @Override
    public void create(Bank bank) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.create(bank);
        }
    }

    @Override
    public void createClientConnection(Bank bank, Client client) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.createClientConnection(bank, client);
        }
    }

    @Override
    public Optional<Bank> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            return bankRepository.read(id);
        }
    }

    @Override
    public void update(Bank bank) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.update(bank);
        }
    }

    @Override
    public void deleteById(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            BankRepository bankRepository = sqlSession.getMapper(BankRepository.class);
            bankRepository.deleteById(deleteId);
        }
    }
}
