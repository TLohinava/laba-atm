package com.solvd.atm.persistence;

import com.solvd.atm.domain.*;

import java.math.BigDecimal;
import java.util.Map;

public interface CashRepository {

    Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap();

}
