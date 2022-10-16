package com.solvd.atm.domain;

import com.solvd.atm.Utils;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.service.CashService;
import com.solvd.atm.service.impl.CashServiceImpl;

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
        Map<BigDecimal, BigDecimal> currentBalance = this.getBalance().stream()
                .filter(i -> i.getCurrencyType().equals(type))
                .collect(Collectors.toMap(Cash::getDenomination, Cash::getQuantity));

        String option = Utils.chooseOptions(currentBalance, sum, scanner);
        updateMap(option, type);
    }

    @Override
    public Boolean checkBalance(BigDecimal sum, CurrencyType type) {
        boolean passedCheck = false;

        BigDecimal atmBalance = this.getBalance().stream()
                .filter(c -> c.getCurrencyType().equals(type))
                .map(c -> c.getQuantity().multiply(c.getDenomination()))
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new QueryException("Cannot sum up the digits"));

        boolean divisionCheck = this.getBalance().stream()
                .filter(c -> c.getCurrencyType().equals(type))
                .map(c -> sum.remainder(c.getDenomination()))
                .anyMatch(d -> d.compareTo(BigDecimal.ZERO) == 0);

        if (sum.compareTo(atmBalance) <= 0 && divisionCheck) {
            passedCheck = true;
        } else if (!divisionCheck) {
            System.out.println("Unfortunately, this ATM doesn't have the sufficient amount of needed banknotes to proceed with your transaction.");
        } else {
            System.out.println("Unfortunately, this ATM doesn't have enough cash to proceed with your transaction.");
        }

        return passedCheck;
    }

    public BigDecimal getMinBanknote(CurrencyType currencyType) {
        return this.getBalance().stream()
                .filter(o -> o.getCurrencyType() == currencyType)
                .min(Comparator.comparing(Cash::getDenomination))
                .map(Cash::getDenomination)
                .orElseThrow(() -> new QueryException("Sorry, there is no such currency"));
    }

    public void updateMap(String option, CurrencyType currencyType) {
        String[] optionArray = option.split(" ");
        String[] innerArray;
        CashService cashService = new CashServiceImpl();
        for (String o : optionArray) {
            innerArray = o.split("x");
            BigDecimal mapKey = new BigDecimal(innerArray[0]);
            Cash cash = cashService.readQuantity(this.id, currencyType, mapKey);
            BigDecimal cashQuantity = cash.getQuantity();
            BigDecimal newCashQuantity = cashQuantity.subtract(new BigDecimal(innerArray[1]));
            cash.setQuantity(newCashQuantity);

            cashService.update(cash);
            cashService.delete();
            this.balance = cashService.read(this.id);
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