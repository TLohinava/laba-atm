package com.solvd.atm.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Atm implements ICheck, IWithdraw {

    private Long id;
    private Address address;
    private Map<CurrencyType, Map<BigDecimal, BigDecimal>> balance;

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType currencyType) {
        BigDecimal atmBalance = new BigDecimal(0);
        Map<BigDecimal, BigDecimal> currencyBalance = this.getBalance().get(currencyType);
        for (Map.Entry<BigDecimal, BigDecimal> entry : currencyBalance.entrySet()) {
            atmBalance = atmBalance.add(entry.getKey().multiply(entry.getValue()));
        }
        return sum.compareTo(atmBalance) <= 0;
    }

    @Override
    public void withdraw(BigDecimal sum) {
        Map<BigDecimal, BigDecimal> withdrawnSum = this.getBalance().get(CurrencyType.BYN);
        Map<CurrencyType, Map<BigDecimal, BigDecimal>> map = new HashMap<>();
        map.put(CurrencyType.BYN, withdrawnSum);
        this.setBalance(map);
    }

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

    public Map<CurrencyType, Map<BigDecimal, BigDecimal>> getBalance() {
        return balance;
    }

    public void setBalance(Map<CurrencyType, Map<BigDecimal, BigDecimal>> balance) {
        this.balance = balance;
    }
}