package com.solvd.atm.domain;

import java.util.Map;

public class Atm {

    Map<String, Long> atmBalance;

    public Map<String, Long> getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(Map<String, Long> atmBalance) {
        this.atmBalance = atmBalance;
    }
}
