package com.solvd.atm.service;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CashService {

    Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap();

    List<Cash> read();

    void create(Long atmId, List<Cash> cashList);

    void updateBatch(List<Cash> cashList);

    Optional<BigDecimal> getMinBanknote(CurrencyType currencyType);

}
