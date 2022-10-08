package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.domain.exception.CurrencyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {

    private static final Logger LOGGER = LogManager.getLogger(Utils.class);
    private static final int CURRENCY_TYPE_NUMBER = 5;

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
        boolean checkAtm = atm.checkBalance(sum, choiceCurrency());
        boolean checkCard = card.checkBalance(sum, card.getCurrencyType());
        if (checkAtm && checkCard) {
            card.withdraw(sum);
            atm.withdraw(sum);
        }
    }

    public static CurrencyType choiceCurrency() {
        CurrencyType currencyType = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter currency: 1. BYN 2. RUB 3. EUR 4. USD 5. CNY");
        try {
            int inputCurrency = scanner.nextInt();
            if (inputCurrency < 1 && inputCurrency > CURRENCY_TYPE_NUMBER) {
                throw new CurrencyException("Sorry, the currency type doesn't exist");
            }
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
                    LOGGER.error("Sorry, the currency you selected is incorrect!");
                    break;
            }
        } catch (InputMismatchException e) {
            throw new CurrencyException("Еhe input type is incorrect, enter a digit from 1 to 5");
        }
        return currencyType;
    }
}