package com.solvd.atm.service;

import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.Map;

public interface CashService {

    Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap();

}
