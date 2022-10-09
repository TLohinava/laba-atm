package com.solvd.atm.domain;

import java.math.BigDecimal;

public interface ICheck {

    Boolean checkBalance(BigDecimal sum, CurrencyType type);

}