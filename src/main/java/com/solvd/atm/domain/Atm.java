package com.solvd.atm.domain;

import com.solvd.atm.Utils;

import java.math.BigDecimal;
import java.util.*;

public class Atm implements ICheck, IWithdraw, IConvert {

    private Long id;
    private Address address;
    private Map<CurrencyType, Map<BigDecimal, BigDecimal>> balance;
    
    public BigDecimal changeCurrencyType(BigDecimal sum, CurrencyType inputType, CurrencyType cardType) {
        return Utils.convertInputType(sum, inputType, cardType);
    }

    @Override
    public void withdraw(BigDecimal sum) {}

    public void withdraw(BigDecimal sum, Scanner scanner) {
        Map<BigDecimal, BigDecimal> currentBalance = this.getBalance().get(CurrencyType.BYN);
        Utils.updateMap(currentBalance, sum, scanner);
    }

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        boolean passedCheck = false;
        Set<CurrencyType> availableTypes = this.getBalance().keySet();
        BigDecimal availableSum = BigDecimal.ZERO;
        
        if (availableTypes.contains(type)) {
            for(Map.Entry<BigDecimal, BigDecimal> entry: this.getBalance().get(type).entrySet()){
                BigDecimal multiplySum = entry.getKey().multiply(entry.getValue());
                availableSum = availableSum.add(multiplySum);
            }
        } else {
            System.out.println("Sorry, the atm doesn't contain the type of currency you selected!");
        }
        
        if (sum.compareTo(availableSum) <= 0) {
            passedCheck = true;
        } else {
            System.out.println("Unfortunately, this ATM doesn't have enough cash to continue with your transaction.");
        }
        return passedCheck;
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