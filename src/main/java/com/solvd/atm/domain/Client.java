package com.solvd.atm.domain;

import com.solvd.atm.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class Client implements IUseATM {

    private static final Logger LOGGER = LogManager.getLogger(Utils.class);

    private Long id;
    private String name;
    private String surname;
    private Account account;

    @Override
    public void getMenu(Card card) {
        Scanner input = new Scanner(System.in);
        boolean correctData = true;

        LOGGER.info("--------- Welcome to the ATM ---------");
        for (int i = 1; i <= 3; i++) {
            LOGGER.info("Please enter your pincode:");
            Integer inputPin = input.nextInt();
            if (inputPin.equals(card.getPin())) {
                correctData = true;
                break;
            } else {
                if (i <= 2) {
                    LOGGER.info(String.format("Sorry, the pincode is wrong, you still have %s chances!", (3 - i)));
                } else {
                    LOGGER.info("Sorry, your card is blocked!");
                    break;
                }
                correctData = false;
            }
        }
        if (correctData) {
            char answer = 'Y';
            while (answer == 'Y') {
                LOGGER.info("Please select the function: 1. Withdrawal 2. Check balance 3. Exit");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        LOGGER.info("---> Withdrawal");
                        LOGGER.info("Please enter the withdrawal amount:");
                        BigDecimal sum = input.nextBigDecimal();
                        BigDecimal balance = card.getBalance();
                        if (sum.compareTo(BigDecimal.ZERO) > 0) {
                            if (sum.compareTo(balance) <= 0) {
                                LOGGER.info("Please take your cash!");
                                card.setBalance(balance.subtract(sum));
                            } else {
                                LOGGER.info("Sorry, not enough funds on your balance!");
                            }
                        } else {
                            LOGGER.info("Please enter the correct amount:");
                        }
                        break;
                    case 2:
                        LOGGER.info("---> Check balance");
                        BigDecimal currentBalance = card.getBalance();
                        LOGGER.info(String.format("Balance on your card: %s", currentBalance));
                        break;
                    case 3:
                        LOGGER.info("---> Exit");
                        LOGGER.info("Thank you for use!");
                        return;
                    default:
                        LOGGER.info("Sorry, the function you selected is incorrect!");
                        break;
                }
                LOGGER.info("Continue? Y(Yes) / N(No)");
                answer = input.next().charAt(0);
            }
            LOGGER.info("Thank you for use!");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}