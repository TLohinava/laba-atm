package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.persistence.*;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class AtmMapperImpl implements AtmRepository {

    @Override
    public void create(Long bankId, Long addressId, Atm atm) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.create(bankId, addressId, atm);
        }
    }

    @Override
    public Optional<Atm> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            return atmRepository.read(id);
        }
    }

    @Override
    public void update(Atm atm) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.update(atm);
        }
    }

    @Override
    public void deleteById(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AtmRepository atmRepository = sqlSession.getMapper(AtmRepository.class);
            atmRepository.deleteById(deleteId);
        }
    }
}
