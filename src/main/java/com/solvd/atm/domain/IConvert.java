package com.solvd.atm.domain;

import java.math.BigDecimal;

public interface IConvert {

    BigDecimal changeCurrencyType(BigDecimal sum, CurrencyType inputType, CurrencyType cardType);

}