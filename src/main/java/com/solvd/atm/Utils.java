package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.service.CashService;
import com.solvd.atm.service.impl.CashServiceImpl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {

    private static final int CURRENCY_TYPE_NUMBER = 5;

    public static BigDecimal enterSum(Scanner scanner, CurrencyType currencyType) {
        BigDecimal sum;
        System.out.println("Please enter the required sum: ");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
            if (!checkMinSum(sum, currencyType)) {
                System.out.println("Sorry the sum is less than min");
                sum = enterSum(scanner, currencyType);
            }
        } else {
            System.out.println("Sorry, the sum should be numeric. ");
            sum = enterSum(scanner, currencyType);
        }
        return sum;
    }

    public static boolean checkMinSum(BigDecimal sum, CurrencyType currencyType) {
        CashService cashService = new CashServiceImpl();
        BigDecimal minEntry = cashService.getMinBanknote(currencyType).get();
        if (sum.compareTo(minEntry) < 0) {
            System.out.printf("Sorry, the min sum should be %s and more%n", minEntry);
            return false;
        }
        return true;
    }

    public static void withdrawCash(Atm atm, Card card) {
        Scanner scanner = new Scanner(System.in);
        CurrencyType currencyType = chooseCurrency();
        BigDecimal sum = enterSum(scanner, currencyType);
        boolean checkAtm = atm.checkBalance(sum, currencyType);
        boolean checkCard = card.checkBalance(sum, card.getCurrencyType());
        if (checkAtm && checkCard) {
            card.withdraw(sum);
            atm.withdraw(sum);
        }
    }

    public static CurrencyType chooseCurrency() {
        CurrencyType currencyType = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter currency: 1. BYN 2. RUB 3. EUR 4. USD 5. CNY");
            try {
                int inputCurrency = scanner.nextInt();
                if (inputCurrency < 1 || inputCurrency > CURRENCY_TYPE_NUMBER) {
                    System.out.println("Sorry, the currency type doesn't exist");
                    currencyType = chooseCurrency();
                } else {
                    switch (inputCurrency) {
                        case 1:
                            currencyType = CurrencyType.BYN;
                            break;
                        case 2:
                            currencyType = CurrencyType.RUB;
                            break;
                        case 3:
                            currencyType = CurrencyType.EUR;
                            break;
                        case 4:
                            currencyType = CurrencyType.USD;
                            break;
                        case 5:
                            currencyType = CurrencyType.CNY;
                            break;
                        default:
                            System.out.println("Sorry, the currency you selected is incorrect!");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("The input type is incorrect, enter a digit from 1 to 5");
                currencyType = chooseCurrency();
            }
        }
        return currencyType;
    }
}