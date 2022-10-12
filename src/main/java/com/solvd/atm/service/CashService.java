package com.solvd.atm.service;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CashService {

    Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap();

    List<Cash> read();

    void create(List<Cash> cashList);

    void updateBatch(List<Cash> cashList);
}
