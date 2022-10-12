package com.solvd.atm.service.impl;

import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.persistence.CashRepository;
import com.solvd.atm.persistence.impl.CashMapperImpl;
import com.solvd.atm.service.CashService;

import java.math.BigDecimal;
import java.util.Map;

public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;

    public CashServiceImpl() {
        this.cashRepository = new CashMapperImpl();
    }

    @Override
    public Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap() {
        return cashRepository.getMap();
    }

}