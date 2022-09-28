package com.solvd.atm.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Atm {

    private Long id;
    private Address address;
    private Map<String, BigDecimal> atmBalance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<String, BigDecimal> getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(Map<String, BigDecimal> atmBalance) {
        this.atmBalance = atmBalance;
    }
}