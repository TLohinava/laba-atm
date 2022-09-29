package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {

    Atm atm = new Atm();
    Map<CurrencyType, BigDecimal> firstAtmBalance = new HashMap<>();
    firstAtmBalance.put(CurrencyType.BYN, new BigDecimal(10000));
    atm.setBalance(firstAtmBalance);

    Card card = new Card();
    card.setBalance(new BigDecimal(1000));
    card.setCurrencyType(CurrencyType.BYN);
    }
}