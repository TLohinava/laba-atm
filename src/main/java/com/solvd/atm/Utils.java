package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {

    public static void selectFunction(Atm atm, Card card) {
        Scanner input = new Scanner(System.in);
        boolean correctData = true;

        System.out.println("--------- Welcome to the ATM ---------");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Please enter your pincode:");
            Integer inputPin = input.nextInt();
            if (inputPin.equals(card.getPin())) {
                correctData = true;
                break;
            } else {
                if (i <= 2) {
                    System.out.println(String.format("Sorry, the pincode is wrong, you still have %s chances!", (3 - i)));
                } else {
                    System.out.println("Sorry, your card is blocked!");
                    break;
                }
                correctData = false;
            }
        }
        if (correctData) {
            char answer = 'Y';
            while (answer == 'Y') {
                System.out.println("Please select the function: 1. Withdrawal 2. Check balance 3. Exit");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("---> Withdrawal");
                        withdrawCash(atm, card);
                        break;
                    case 2:
                        System.out.println("---> Check balance");
                        BigDecimal currentBalance = card.getBalance();
                        System.out.println(String.format("Balance on your card: %s", currentBalance));
                        break;
                    case 3:
                        System.out.println("---> Exit");
                        System.out.println("Thank you for use!");
                        return;
                    default:
                        System.out.println("Sorry, the function you selected is incorrect!");
                        break;
                }
                System.out.println("Continue? Y / N");
                answer = input.next().charAt(0);
            }
            System.out.println("Thank you for use!");
        }
    }

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
        CurrencyType inputType = selectCurrencyType();
        CurrencyType cardType = card.getCurrencyType();
        BigDecimal convertSum = atm.changeCurrencyType(sum, inputType, cardType);

        boolean checkAtm = atm.checkBalance(sum, inputType);
        boolean checkCard = card.checkBalance(convertSum, cardType);
        if (checkAtm && checkCard) {
            atm.withdraw(sum, inputType);
            card.withdraw(convertSum, cardType);
            System.out.println("Please take your cash!");
        }
    }

    public static CurrencyType selectCurrencyType() {
        Scanner input = new Scanner(System.in);
        CurrencyType inputType = CurrencyType.BYN;

        System.out.println("What type of currency do you need? 1. BYN 2. RUB 3. EUR 4. USD 5. CNY");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                inputType = CurrencyType.BYN;
                break;
            case 2:
                inputType = CurrencyType.RUB;
                break;
            case 3:
                inputType = CurrencyType.EUR;
                break;
            case 4:
                inputType = CurrencyType.USD;
                break;
            case 5:
                inputType = CurrencyType.CNY;
                break;
            default:
                System.out.println("Sorry, the type of currency you selected is incorrect!");
                break;
        }
        return inputType;
    }

    public static BigDecimal convertInputType(BigDecimal sum, CurrencyType inputType, CurrencyType cardType) {
        switch (inputType) {
            case BYN:
                sum = Utils.convertBYN(sum, cardType);
                break;
            case RUB:
                sum = Utils.convertRUB(sum, cardType);
                break;
            case EUR:
                sum = Utils.convertEUR(sum, cardType);
                break;
            case USD:
                sum = Utils.convertUSD(sum, cardType);
                break;
            case CNY:
                sum = Utils.convertCNY(sum, cardType);
                break;
            default:
                break;
        }
        return sum;
    }

    public static BigDecimal convertBYN(BigDecimal sum, CurrencyType type) {
        switch (type) {
            case BYN:
                sum = sum.multiply(BigDecimal.valueOf(1));
                break;
            case RUB:
                sum = sum.multiply(BigDecimal.valueOf(24.73));
                break;
            case EUR:
                sum = sum.multiply(BigDecimal.valueOf(0.41));
                break;
            case USD:
                sum = sum.multiply(BigDecimal.valueOf(0.387));
                break;
            case CNY:
                sum = sum.multiply(BigDecimal.valueOf(2.81));
                break;
            default:
                break;
        }
        return sum;
    }

    public static BigDecimal convertRUB(BigDecimal sum, CurrencyType currency) {
        switch (currency) {
            case BYN:
                sum = sum.multiply(BigDecimal.valueOf(0.0404));
                break;
            case RUB:
                sum = sum.multiply(BigDecimal.valueOf(1));
                break;
            case EUR:
                sum = sum.multiply(BigDecimal.valueOf(0.016));
                break;
            case USD:
                sum = sum.multiply(BigDecimal.valueOf(0.016));
                break;
            case CNY:
                sum = sum.multiply(BigDecimal.valueOf(0.114));
                break;
            default:
                break;
        }
        return sum;
    }

    public static BigDecimal convertEUR(BigDecimal sum, CurrencyType type) {
        switch (type) {
            case BYN:
                sum = sum.multiply(BigDecimal.valueOf(2.52));
                break;
            case RUB:
                sum = sum.multiply(BigDecimal.valueOf(62.32));
                break;
            case EUR:
                sum = sum.multiply(BigDecimal.valueOf(1));
                break;
            case USD:
                sum = sum.multiply(BigDecimal.valueOf(0.974));
                break;
            case CNY:
                sum = sum.multiply(BigDecimal.valueOf(7.08));
                break;
            default:
                break;
        }
        return sum;
    }

    public static BigDecimal convertUSD(BigDecimal sum, CurrencyType type) {
        switch (type) {
            case BYN:
                sum = sum.multiply(BigDecimal.valueOf(2.58));
                break;
            case RUB:
                sum = sum.multiply(BigDecimal.valueOf(63.98));
                break;
            case EUR:
                sum = sum.multiply(BigDecimal.valueOf(1.027));
                break;
            case USD:
                sum = sum.multiply(BigDecimal.valueOf(1));
                break;
            case CNY:
                sum = sum.multiply(BigDecimal.valueOf(7.27));
                break;
            default:
                break;
        }
        return sum;
    }

    public static BigDecimal convertCNY(BigDecimal sum, CurrencyType type) {
        switch (type) {
            case BYN:
                sum = sum.multiply(BigDecimal.valueOf(0.3555));
                break;
            case RUB:
                sum = sum.multiply(BigDecimal.valueOf(8.8));
                break;
            case EUR:
                sum = sum.multiply(BigDecimal.valueOf(0.14));
                break;
            case USD:
                sum = sum.multiply(BigDecimal.valueOf(0.138));
                break;
            case CNY:
                sum = sum.multiply(BigDecimal.valueOf(1));
                break;
            default:
                break;
        }
        return sum;
    }
}