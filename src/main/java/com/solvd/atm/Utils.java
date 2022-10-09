package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {

    public static void selectFunction(Card card) {
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
                        System.out.println("Please enter the withdrawal amount:");
                        BigDecimal sum = input.nextBigDecimal();
                        BigDecimal balance = card.getBalance();
                        if (sum.compareTo(BigDecimal.ZERO) > 0) {
                            if (sum.compareTo(balance) <= 0) {
                                System.out.println("Please take your cash!");
                                card.setBalance(balance.subtract(sum));
                            } else {
                                System.out.println("Sorry, not enough funds on your balance!");
                            }
                        } else {
                            System.out.println("Please enter the correct amount:");
                        }
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
                System.out.println("Continue? Y(Yes) / N(No)");
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
        boolean checkAtm = atm.checkBalance(sum, CurrencyType.BYN);
        //change currencyType in atm method for week two
        boolean checkCard = card.checkBalance(sum, card.getCurrencyType());
        if (checkAtm && checkCard) {
            card.withdraw(sum);
            atm.withdraw(sum);
        }
    }
}