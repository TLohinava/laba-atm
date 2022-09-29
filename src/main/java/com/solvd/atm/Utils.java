package com.solvd.atm;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {

    public static BigDecimal enterSum() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum = null;
        System.out.println("Please enter the required sum: ");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
        } else {
            System.out.println("Sorry, your data is invalid. Please enter the digits.");
            sum = enterSum();
        }
        return sum;
    }
}