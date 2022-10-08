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
//        return sum.compareTo(this.getBalance().get(currencyType)) <= 0;
        return true;
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

    @Override
    public String toString() {
        return "Atm{" +
                "id=" + id +
                ", address=" + address +
                ", balance=" + balance +
                '}';
    }
}