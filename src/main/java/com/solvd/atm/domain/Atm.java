package com.solvd.atm.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Atm {

    private Long id;
    private Address address;
    private Map<String, BigDecimal> balance;

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

    public Map<String, BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(Map<String, BigDecimal> balance) {
        this.balance = balance;
    }
}