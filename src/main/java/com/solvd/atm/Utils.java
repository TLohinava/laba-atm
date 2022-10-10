package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
                System.out.println("Continue? Y(Yes) / N(No)");
                answer = input.next().charAt(0);
            }
            System.out.println("Thank you for use!");
        }
    }

    public static BigDecimal enterSum() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum;
        System.out.println("Please enter the withdrawal amount:");
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

    public static List<String> chooseDenomination(Map<BigDecimal, BigDecimal> map, BigDecimal sum) {
        List<String> options = new ArrayList<>();
        String option = "";
        BigDecimal mapSum = new BigDecimal(0);

        for (Map.Entry<BigDecimal, BigDecimal> entry : map.entrySet()) {
            BigDecimal amount = entry.getKey().multiply(entry.getValue());
            BigDecimal difference = amount.subtract(sum);
            mapSum = mapSum.add(amount);

            if (difference.compareTo(BigDecimal.ZERO) >= 0 && sum.compareTo(entry.getKey()) >= 0) {
                BigDecimal quantity = sum.divide(entry.getKey(), RoundingMode.FLOOR);
                BigDecimal rest = sum.subtract(entry.getKey().multiply(quantity));
                option = entry.getKey() + "x" + quantity;

                while (rest.compareTo(BigDecimal.ZERO) > 0) {
                    for (Map.Entry<BigDecimal, BigDecimal> subEntry : map.entrySet()) {
                        BigDecimal subRest = rest.divide(subEntry.getKey(), RoundingMode.FLOOR);
                        if (subRest.compareTo(BigDecimal.ZERO) > 0) {
                            option += " " + subEntry.getKey() + "x" + subRest;
                            rest = rest.subtract(subEntry.getKey().multiply(subRest));
                        }
                    }
                }
                options.add(option);
            }
        }

        if (option.isEmpty() && sum.compareTo(mapSum) <= 0) {
            Map<BigDecimal, BigDecimal> copyMap = new HashMap<>(map);
            while (sum.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal maxKey = Collections.max(copyMap.keySet());
                BigDecimal maxValue = copyMap.get(maxKey);

                if (sum.compareTo(maxKey) >= 0) {
                    BigDecimal val = sum.divide(maxKey, RoundingMode.FLOOR);
                    if (val.compareTo(maxValue) >= 0) {
                        val = maxValue;
                    }
                    option += " " + maxKey + "x" + val;
                    sum = sum.subtract(maxKey.multiply(val));
                    copyMap.remove(maxKey);
                }
            }
            options.add(option.trim());
        }
        System.out.println("Available option for the entered sum:");
        options.forEach(r -> System.out.println((options.indexOf(r) + 1) + ". " + r));
        return options;
    }

    public static String chooseOptions(Map<BigDecimal, BigDecimal> map, BigDecimal sum) {
        List<String> options = chooseDenomination(map, sum);
        String chosenOption = "";
        Scanner digit = new Scanner(System.in);
        System.out.println("Please choose your option:");
        int chosenDigit = digit.nextInt();
        if ((options.size() >= chosenDigit) && (chosenDigit > 0)) {
            chosenOption += options.get(chosenDigit - 1);
        } else {
            System.out.println("The option you've entered is not available. Please try again");
            chooseOptions(map, sum);
        }
        System.out.println("Please take your cash!");
        return chosenOption;
    }

    public static void updateMap(Map<BigDecimal, BigDecimal> map, BigDecimal sum) {
        String option = chooseOptions(map, sum);
        String[] optionArray = option.split(" ");
        String[] innerArray;
        for (String o : optionArray) {
            innerArray = o.split("x");
            map.replace(new BigDecimal(innerArray[0]), map.get(new BigDecimal(innerArray[0])).subtract(new BigDecimal(innerArray[1])));
        }
    }
}