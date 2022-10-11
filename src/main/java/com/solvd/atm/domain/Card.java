package com.solvd.atm.domain;

import java.math.BigDecimal;

public class Card implements ICheck, IWithdraw {

    private Long id;
    private Long number;
    private Integer pin;
    private BigDecimal balance;
    private CurrencyType currencyType;

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        Boolean correctData = false;
        if(sum.compareTo(this.getBalance()) <= 0 && type.equals(this.getCurrencyType())) {
            correctData = true;
        } else {
            System.out.println("Sorry, not enough funds on your balance!");
        }
        return correctData;
    }

    @Override
    public void withdraw(BigDecimal sum, CurrencyType type) {
        if (type.equals(this.getCurrencyType())) {
            BigDecimal withdrawnSum = this.getBalance().subtract(sum);
            this.setBalance(withdrawnSum);
        }
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

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
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