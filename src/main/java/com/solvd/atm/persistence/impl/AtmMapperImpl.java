package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.persistence.AtmRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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

    public Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap() {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            class AtmHandler implements ResultHandler {
                final Map<CurrencyType, Map<BigDecimal, BigDecimal>> atmCash = new HashMap<CurrencyType, Map<BigDecimal, BigDecimal>>();

                @Override
                public void handleResult(ResultContext context) {
                    final Cash complex = (Cash)context.getResultObject();
                    if (!atmCash.containsKey(complex.getCurrencyType())) {
                        atmCash.put(complex.getCurrencyType(), new HashMap<BigDecimal, BigDecimal>());
                    }
                    atmCash.get(complex.getCurrencyType()).put(complex.getDenomination(), complex.getQuantity());
                }
            }
            AtmHandler handler = new AtmHandler();
            sqlSession.select("com.solvd.atm.persistence.AtmRepository.getMap", null, handler);

            return handler.atmCash;
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
