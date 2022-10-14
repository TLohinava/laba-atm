package com.solvd.atm;

import com.solvd.atm.domain.*;
import com.solvd.atm.service.CashService;
import com.solvd.atm.service.impl.CashServiceImpl;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.RoundingMode;
import java.util.*;

public class Utils {

    private static final int CURRENCY_TYPE_NUMBER = 5;

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

    public static BigDecimal enterSum(CurrencyType currencyType) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum;
        System.out.println("Please enter the withdrawal amount:");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
            if (sum.compareTo(BigDecimal.ZERO) <= 0) {
                System.out.println("Sorry, the sum cannot be 0 or less. ");
                sum = enterSum(currencyType);
            } else if (!checkMinSum(sum, currencyType)) {
                System.out.println("Sorry the sum is less than min");
                sum = enterSum(currencyType);
            }
        } else {
            System.out.println("Sorry, the sum should be numeric. ");
            sum = enterSum(currencyType);
        }
        return sum;
    }

    public static boolean checkMinSum(BigDecimal sum, CurrencyType currencyType) {
        CashService cashService = new CashServiceImpl();
        if (sum.compareTo(cashService.getMinBanknote(currencyType).get()) < 0) {
            System.out.println("Sorry, the min sum should be 5 and more");
            return false;
        }
        return true;
    }

    public static void withdrawCash(Atm atm, Card card) {
        CurrencyType inputType = selectCurrencyType();
        BigDecimal sum = enterSum(inputType);
        CurrencyType cardType = card.getCurrencyType();
        BigDecimal convertSum = atm.changeCurrencyType(sum, inputType, cardType);

        boolean checkAtm = atm.checkBalance(sum, inputType);
        boolean checkCard = card.checkBalance(convertSum, cardType);

        if (checkAtm && checkCard) {
            atm.withdraw(sum);
            card.withdraw(convertSum);
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

    public static void updateMap(Map<BigDecimal, BigDecimal> map, BigDecimal sum) {
        String option = chooseOptions(map, sum);
        String[] optionArray = option.split(" ");
        String[] innerArray;
        for (String o : optionArray) {
            innerArray = o.split("x");
            BigDecimal mapValue = map.get(new BigDecimal(innerArray[0]));
            BigDecimal newValue = mapValue.subtract(new BigDecimal(innerArray[1]));
            map.replace(new BigDecimal(innerArray[0]), newValue);
        }
    }
}