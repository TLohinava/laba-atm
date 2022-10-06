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

    Map<BigDecimal, BigDecimal> map = new HashMap<>();
    map.put(new BigDecimal(10), new BigDecimal(15));
    map.put(new BigDecimal(20), new BigDecimal(10));
    map.put(new BigDecimal(50), new BigDecimal(10));

    Utils.chooseDenomination(map, Utils.enterSum());
    }
}