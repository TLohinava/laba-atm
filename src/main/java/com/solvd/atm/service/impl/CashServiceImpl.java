package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.CashRepository;
import com.solvd.atm.persistence.impl.CashMapperImpl;
import com.solvd.atm.service.CashService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
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

    @Override
    public List<Cash> read() {
        return cashRepository.read();
    }

    @Override
    public void create(Long atmId, List<Cash> cashList) {
        cashRepository.create(atmId, cashList);
    }

    @Override
    public void updateBatch(List<Cash> cashList) {
        cashRepository.updateBatch(cashList);
    }

    @Override
    public BigDecimal getMinBanknote(CurrencyType currencyType) {
        return cashRepository.read().stream()
                .filter(o -> o.getCurrencyType() == currencyType)
                .min(Comparator.comparing(Cash::getDenomination))
                .map(Cash::getDenomination)
                .orElseThrow(() -> new QueryException("Sorry, there is no such currency"));
    }
}