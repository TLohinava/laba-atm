package com.solvd.atm;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Card;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
        //change currencyType in atm method for week two
        boolean checkCard = card.checkBalance(sum, card.getCurrencyType());
        if (checkAtm && checkCard) {
            card.withdraw(sum);
            atm.withdraw(sum);
        }
    }

    public static void chooseDenomination(Map<BigDecimal, BigDecimal> map, BigDecimal sum) {
        List<String> options = new ArrayList<>();
        String option = "";
        BigDecimal mapSum = new BigDecimal(0);

        for (Map.Entry<BigDecimal, BigDecimal> entry : map.entrySet()) {
            BigDecimal multipliedEntry = entry.getKey().multiply(entry.getValue());
            BigDecimal subtracted = multipliedEntry.subtract(sum);
            mapSum = mapSum.add(multipliedEntry);

            if (subtracted.compareTo(BigDecimal.ZERO) >= 0 && sum.compareTo(entry.getKey()) >= 0) {
                BigDecimal quantity = sum.divide(entry.getKey(), RoundingMode.FLOOR);
                option = entry.getKey() + "x" + quantity;

                if (sum.remainder(entry.getKey()).compareTo(BigDecimal.ZERO) != 0) {
                    BigDecimal rest = sum.remainder(entry.getKey());

                    for (Map.Entry<BigDecimal, BigDecimal> subEntry : map.entrySet()) {
                        BigDecimal subRest = rest.divide(subEntry.getKey(), RoundingMode.FLOOR);

                        if (subRest.compareTo(BigDecimal.ZERO) > 0) {
                            option += " " + subEntry.getKey() + "x" + subRest;
                            break;
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

        System.out.println(options);
    }
}