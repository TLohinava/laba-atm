package com.solvd.atm.domain;

import com.solvd.atm.Utils;

import java.math.BigDecimal;
import java.util.*;

public class Atm implements ICheck, IWithdraw, IConvert {

    private Long id;
    private Address address;
    private Map<CurrencyType, Map<BigDecimal, BigDecimal>> balance;

    @Override
    public BigDecimal changeCurrencyType(BigDecimal sum, CurrencyType inputType, CurrencyType cardType) {
        return Utils.convertInputType(sum, inputType, cardType);
    }

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        Set<CurrencyType> availableTypes = this.getBalance().keySet();
        BigDecimal availableSum = BigDecimal.ZERO;
        Boolean correctData = false;

        if (availableTypes.contains(type)) {
            for(Map.Entry<BigDecimal, BigDecimal> entry: this.getBalance().get(type).entrySet()){
                BigDecimal multiplySum = entry.getKey().multiply(entry.getValue());
                availableSum = availableSum.add(multiplySum);
            }
        } else {
            System.out.println("Sorry, the atm doesn't contain the type of currency you selected!");
        }
        if (sum.compareTo(availableSum) <= 0) {
            correctData = true;
        }  else {
            System.out.println("Sorry, not enough funds on atm's balance!");
        }
        return correctData;
    }

    @Override
    public void withdraw(BigDecimal sum, CurrencyType type) {
//      add method of choosing denomination
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the denomination");
        BigDecimal inputDenomination = input.nextBigDecimal();
//
        Set<CurrencyType> availableTypes = this.getBalance().keySet();
        if (availableTypes.contains(type)) {
            this.getBalance().get(type).entrySet().stream()
                    .filter(entry -> entry.getKey().equals(inputDenomination))
                    .forEach(entry -> this.getBalance().get(type).put(inputDenomination,
                            entry.getValue().subtract(sum.divide(inputDenomination, 0))));
        }
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