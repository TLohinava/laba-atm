package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {

    public static BigDecimal enterSum() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum;
        System.out.println("Please enter the required sum: ");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
            if (sum.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Sorry, the sum cannot be 0 or less. ");
                sum = enterSum();
            }
        } else {
            System.out.println("Sorry, the sum should be numeric. ");
            sum = enterSum();
        }
        return sum;
    }

    public static void withdrawCash(Atm atm, Card card) {
        BigDecimal sum = enterSum();
        boolean checkAtm = atm.checkBalance(sum, CurrencyType.BYN);
        boolean checkCard = card.checkBalance(sum, card.getCurrencyType());
        if (checkAtm && checkCard) {
            card.withdraw(sum);
            atm.withdraw(sum);
        }
    }

    public static  currencySelection(Atm atm, CurrencyType currencyType){
        Scanner scanner = new Scanner(System.in);

    }

    public static String getResult(BigDecimal inCount, int from, int to) {
        BigDecimal result = BigDecimal.valueOf(0);
        switch (from) {
            case 0:
                switch (to) {
                    case 0:
                        break;
                    case 1:
                        result = inCount / getUSDrate();
                        break;
                    case 2:
                        result = inCount / getEURrate();
                        ;
                        break;
                    case 3:
                        result = inCount / getUAHrate() * 10;
                        break;
                }
                break;
            case 1:
                switch (to) {
                    case 0:
                        result = inCount * getUSDrate();
                        break;
                    case 1:

                        break;
                    case 2:
                        result = inCount * getUSDrate() / getEURrate();
                        break;
                    case 3:
                        result = inCount * getUSDrate() / getUAHrate() * 10;
                        break;
                }
                break;
            case 2:
                switch (to) {
                    case 0:
                        result = inCount * getEURrate();
                        break;
                    case 1:
                        result = inCount * getEURrate() / getUSDrate();
                        break;
                    case 2:

                        break;
                    case 3:
                        result = inCount * getEURrate() / getUAHrate() * 10;
                        break;
                }
                break;
            case 3:
                switch (to) {
                    case 0:
                        result = inCount * getUAHrate() / 10;
                        break;
                    case 1:
                        result = inCount * getUAHrate() / 10 / getUSDrate();
                        break;
                    case 2:
                        result = inCount * (getUAHrate() / 10) / getEURrate();
                        break;
                    case 3:
                        break;
                }
                break;
        }
        String s = String.format("%.4f", result);
        return s;
    }
}
