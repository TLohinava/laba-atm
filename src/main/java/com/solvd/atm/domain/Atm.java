package com.solvd.atm.domain;

import com.solvd.atm.Utils;

import java.math.BigDecimal;
import java.util.Map;

public class Atm implements ICheck, IWithdraw {

    private Long id;
    private Address address;
    private Map<CurrencyType, Map<BigDecimal, BigDecimal>> balance;

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType currencyType) {
        boolean passedCheck = false;
        BigDecimal atmBalance = new BigDecimal(0);
        Map<BigDecimal, BigDecimal> currencyBalance = this.getBalance().get(currencyType);
        for (Map.Entry<BigDecimal, BigDecimal> entry : currencyBalance.entrySet()) {
            BigDecimal amount = entry.getKey().multiply(entry.getValue());
            atmBalance = atmBalance.add(amount);
        }
        if (sum.compareTo(atmBalance) <= 0) {
            passedCheck = true;
        } else {
            System.out.println("Unfortunately, this ATM doesn't have enough cash to continue with your transaction.");
        }
        return passedCheck;
    }

    @Override
    public void withdraw(BigDecimal sum) {
        Map<BigDecimal, BigDecimal> currentBalance = this.getBalance().get(CurrencyType.BYN);
        Utils.updateMap(currentBalance, sum);
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