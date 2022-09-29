package com.solvd.atm.domain;

import java.math.BigDecimal;

public class Card implements ICheck, IWithdraw {

    private Long id;
    private Long number;
    private CurrencyType currencyType;
    private BigDecimal balance;

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        return sum.compareTo(this.getBalance()) <= 0 && type.equals(this.getCurrencyType());
    }

    @Override
    public void withdraw(BigDecimal sum) {
        BigDecimal withdrawnSum = this.getBalance().subtract(sum);
        this.setBalance(withdrawnSum);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}