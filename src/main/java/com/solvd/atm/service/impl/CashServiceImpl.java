package com.solvd.atm.service.impl;

import com.solvd.atm.domain.*;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.CashRepository;
import com.solvd.atm.persistence.impl.CashMapperImpl;
import com.solvd.atm.service.CashService;

import java.math.BigDecimal;

import java.util.*;

public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;

    public CashServiceImpl() {
        this.cashRepository = new CashMapperImpl();
    }

    @Override
    public List<Cash> read(Long atmId) {
        return cashRepository.read(atmId);
    }

    @Override
    public Cash readQuantity(Long atmId, CurrencyType currencyType, BigDecimal denomination) {
        return cashRepository.readQuantity(atmId, currencyType, denomination)
                .orElseThrow(() -> new QueryException("No cash found"));
    }

    @Override
    public void create(Long atmId, List<Cash> cashList) {
        cashRepository.create(atmId, cashList);
    }

    @Override
    public void update(Cash cash) {
        cashRepository.update(cash);
    }

    @Override
    public void delete() {
        cashRepository.delete();
    }
}