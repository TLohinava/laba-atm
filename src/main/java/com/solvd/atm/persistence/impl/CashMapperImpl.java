package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.persistence.CashRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CashMapperImpl implements CashRepository {

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
