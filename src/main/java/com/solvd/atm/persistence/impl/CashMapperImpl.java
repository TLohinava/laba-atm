package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.*;
import com.solvd.atm.persistence.*;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.*;

public class CashMapperImpl implements CashRepository {

    @Override
    public List<Cash> read() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            return mapper.read();
        }
    }

    @Override
    public Optional<Cash> readQuantity(Long atmId, CurrencyType currencyType, BigDecimal denomination) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            return mapper.readQuantity(atmId, currencyType, denomination);
        }
    }

    @Override
    public void create(Long atmId, List<Cash> cashList) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            mapper.create(atmId, cashList);
        }
    }

    @Override
    public void update(Cash cash) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            CashRepository mapper = session.getMapper(CashRepository.class);
            mapper.update(cash);
        }
    }
}