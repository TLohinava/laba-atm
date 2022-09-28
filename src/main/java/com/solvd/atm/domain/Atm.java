package com.solvd.atm.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Atm {

    private Map<String, BigDecimal> atmBalance;

    public Map<String, BigDecimal> getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(Map<String, BigDecimal> atmBalance) {
        this.atmBalance = atmBalance;
    }
}