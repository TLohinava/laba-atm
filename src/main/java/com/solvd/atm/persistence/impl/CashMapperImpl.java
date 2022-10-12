package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.persistence.AtmRepository;
import com.solvd.atm.persistence.CashRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashMapperImpl implements CashRepository {

    @Override
    public List<Cash> read() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            return mapper.read();
        }
    }

    @Override
    public void create(List<Cash> cashList) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            mapper.create(cashList);
        }
    }

    @Override
    public void update(Cash cash) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
                mapper.update(cash);
        }
    }

    @Override
    public void updateBatch(List<Cash> cashList) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            for(Cash cash : cashList) {
                mapper.update(cash);
            }
        }
    }

    @Override
    public BigDecimal getMinBanknote(CurrencyType currencyType) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository cashRepository = sqlSession.getMapper(CashRepository.class);
            return cashRepository.getMinBanknote(currencyType);
        }
    }

    @Override
    public Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap() {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            class AtmHandler implements ResultHandler {
                final Map<CurrencyType, Map<BigDecimal, BigDecimal>> atmCash = new HashMap<>();

                @Override
                public void handleResult(ResultContext context) {
                    final Cash complex = (Cash)context.getResultObject();
                    if (!atmCash.containsKey(complex.getCurrencyType())) {
                        atmCash.put(complex.getCurrencyType(), new HashMap<>());
                    }
                    atmCash.get(complex.getCurrencyType()).put(complex.getDenomination(), complex.getQuantity());
                }
            }
            AtmHandler handler = new AtmHandler();
            sqlSession.select("com.solvd.atm.persistence.CashRepository.getMap", null, handler);

            return handler.atmCash;
        }
    }
}
