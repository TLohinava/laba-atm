package com.solvd.atm;

import com.solvd.atm.domain.*;

import com.solvd.atm.persistence.ConnectionPool;
import com.solvd.atm.service.*;
import com.solvd.atm.service.impl.*;

import java.math.*;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.util.*;

public class Utils {

    private final static TransactionService TRANSACTION_SERVICE = new TransactionServiceImpl();

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public static void selectFunction(Atm atm, Card card) {
        try (Scanner input = new Scanner(System.in)) {
            boolean correctData = true;

            System.out.println("--------- Welcome to the ATM ---------");
            for (int i = 1; i <= 3; i++) {
                System.out.println("Please enter your pincode:");
                Transaction transaction = new Transaction();
                Integer inputPin = input.nextInt();
                if (inputPin.equals(card.getPin())) {
                    correctData = true;
                    transaction.setDateTime(LocalDateTime.now());
                    transaction.setMessage("The pincode entered ");
                    transaction.setResult(Transaction.Result.SUCCESSFULLY);
                    TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
                    break;
                } else {
                    if (i <= 2) {
                        System.out.printf("Sorry, the pincode is wrong, you still have %s chances!%n", (3 - i));
                    } else {
                        transaction.setDateTime(LocalDateTime.now());
                        transaction.setMessage("The card is blocked");
                        transaction.setResult(Transaction.Result.UNSUCCESSFULLY);
                        TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
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
                            withdrawCash(atm, card, input);
                            break;
                        case 2:
                            System.out.println("---> Check balance");
                            BigDecimal currentBalance = card.getBalance();
                            Transaction transaction = new Transaction();
                            transaction.setDateTime(LocalDateTime.now());
                            transaction.setMessage("The balance on the card is " + currentBalance);
                            transaction.setResult(Transaction.Result.SUCCESSFULLY);
                            TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
                            System.out.printf("Balance on your card: %s%n", currentBalance);
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
    }

    public static BigDecimal enterSum(Atm atm, Scanner scanner, CurrencyType currencyType) {
        BigDecimal sum;
        System.out.println("Please enter the required sum: ");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
            if (!checkMinSum(atm, sum, currencyType)) {
                sum = enterSum(atm, scanner, currencyType);
            }
        } else {
            System.out.println("Sorry, the sum should be numeric. ");
            sum = enterSum(atm, scanner, currencyType);
        }
        return sum;
    }

    public static void withdrawCash(Atm atm, Card card, Scanner scanner) {
        CurrencyType inputType = selectCurrencyType(scanner);
        BigDecimal sum = enterSum(atm, scanner, inputType);
        CurrencyType cardType = card.getCurrencyType();
        BigDecimal convertSum = atm.changeCurrencyType(sum, inputType, cardType);
        Transaction transaction = new Transaction();
        if (inputType != cardType) {
            transaction.setDateTime(LocalDateTime.now());
            transaction.setMessage("Cash " + sum + " " + inputType);
            transaction.setResult(Transaction.Result.SUCCESSFULLY);
            TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
        }
        boolean checkAtm = atm.checkBalance(sum, inputType);
        boolean checkCard = card.checkBalance(convertSum, cardType);
        if (checkAtm && checkCard) {
            atm.withdraw(sum, scanner, inputType);
            card.withdraw(convertSum);
            transaction.setDateTime(LocalDateTime.now());
            transaction.setMessage("Cash was withdrawn in the amount of " + convertSum + " " + cardType);
            transaction.setResult(Transaction.Result.SUCCESSFULLY);
            TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
            System.out.println("Please take your cash!");
        } else {
            transaction.setDateTime(LocalDateTime.now());
            transaction.setMessage("No cash was withdrawn " + sum);
            transaction.setResult(Transaction.Result.UNSUCCESSFULLY);
            TRANSACTION_SERVICE.create(atm.getId(), card.getId(), transaction);
        }
    }

    public static boolean checkMinSum(Atm atm, BigDecimal sum, CurrencyType currencyType) {
        BigDecimal minBanknote = atm.getMinBanknote(currencyType);
        if (sum.compareTo(minBanknote) < 0) {
            System.out.printf("Sorry, the min sum should be %s and more%n", minBanknote);
            return false;
        }
        return true;
    }

    public static CurrencyType selectCurrencyType(Scanner input) {
        CurrencyType inputType;

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
                inputType = selectCurrencyType(input);
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
                sum = sum.multiply(BigDecimal.valueOf(0.017));
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
        BigDecimal minKey = Collections.min(map.keySet());

        for (Map.Entry<BigDecimal, BigDecimal> entry : map.entrySet()) {
            BigDecimal amount = entry.getKey().multiply(entry.getValue());
            BigDecimal difference = amount.subtract(sum);
            mapSum = mapSum.add(amount);

            if (difference.compareTo(BigDecimal.ZERO) >= 0 && sum.compareTo(entry.getKey()) >= 0) {
                BigDecimal quantity = sum.divide(entry.getKey(), RoundingMode.FLOOR);
                BigDecimal rest = sum.subtract(entry.getKey().multiply(quantity));
                option = entry.getKey() + "x" + quantity;

                while (rest.compareTo(BigDecimal.ZERO) > 0) {
                    if (rest.remainder(minKey).compareTo(BigDecimal.ZERO) == 0) {
                        for (Map.Entry<BigDecimal, BigDecimal> subEntry : map.entrySet()) {
                            BigDecimal subRest = rest.divide(subEntry.getKey(), RoundingMode.FLOOR);
                            if (subRest.compareTo(BigDecimal.ZERO) > 0) {
                                option += " " + subEntry.getKey() + "x" + subRest;
                                rest = rest.subtract(subEntry.getKey().multiply(subRest));
                            }
                        }
                    } else {
                        rest = BigDecimal.ZERO;
                        quantity = BigDecimal.ZERO;
                    }
                }
                if (quantity.compareTo(BigDecimal.ZERO) > 0) {
                    options.add(option);
                }
            }
        }

        System.out.println("Available option for the entered sum:");
        options.forEach(r -> System.out.println((options.indexOf(r) + 1) + ". " + r));
        return options;
    }

    public static String chooseOptions(Map<BigDecimal, BigDecimal> map, BigDecimal sum, Scanner scanner) {
        List<String> options = chooseDenomination(map, sum);
        String chosenOption = "";
        System.out.println("Please choose your option:");
        int chosenDigit = scanner.nextInt();
        if ((options.size() >= chosenDigit) && (chosenDigit > 0)) {
            chosenOption += options.get(chosenDigit - 1);
        } else {
            System.out.println("The option you've entered is not available. Please try again");
            chooseOptions(map, sum, scanner);
        }
        return chosenOption;
    }

    public static Runnable synchronizeObjects(Client client, Atm atm, Card card) {
        Runnable synchronization = () -> {
            synchronized (atm) {
                synchronized (card) {
                    Connection connection = CONNECTION_POOL.getConnection();
                    client.getMenu(atm, card);
                    CONNECTION_POOL.releaseConnection(connection);
                }
            }
        };
        return synchronization;
    }
}