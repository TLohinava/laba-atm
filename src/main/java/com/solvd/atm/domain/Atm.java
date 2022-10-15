package com.solvd.atm.domain;

import com.solvd.atm.Utils;
import com.solvd.atm.domain.exception.QueryException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Atm implements ICheck, IWithdraw, IConvert {

    private Long id;
    private Address address;
    private List<Cash> balance;

    public BigDecimal changeCurrencyType(BigDecimal sum, CurrencyType inputType, CurrencyType cardType) {
        return Utils.convertInputType(sum, inputType, cardType);
    }

    @Override
    public void withdraw(BigDecimal sum) {
    }

    public void withdraw(BigDecimal sum, Scanner scanner, CurrencyType type) {
        Map<CurrencyType, Map<BigDecimal, BigDecimal>> balanceMap = this.getBalance().stream()
                .collect(Collectors.groupingBy(Cash::getCurrencyType,
                        Collectors.toMap(Cash::getDenomination, Cash::getQuantity)));
        Map<BigDecimal, BigDecimal> currentBalance = balanceMap.get(type);
        String option = Utils.chooseOptions(currentBalance, sum, scanner);
        Utils.updateMap(this.id, option, type);
    }

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        boolean passedCheck = false;

        BigDecimal atmBalance = this.getBalance().stream()
                .filter(c -> c.getCurrencyType() == type)
                .map(c -> c.getQuantity().multiply(c.getDenomination()))
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new QueryException("Cannot sum up the digits"));

        if (sum.compareTo(atmBalance) <= 0) {
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

    public List<Cash> getBalance() {
        return balance;
    }

    public void setBalance(List<Cash> balance) {
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