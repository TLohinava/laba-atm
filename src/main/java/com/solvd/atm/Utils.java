package com.solvd.atm;

import java.math.BigDecimal;
import java.util.Scanner;

public class Utils {

    public static BigDecimal enterSum() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal sum;
        System.out.println("Please enter the required sum: ");
        if (scanner.hasNextBigDecimal()) {
            sum = scanner.nextBigDecimal();
            if(sum.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Sorry, the sum cannot be 0. ");
                sum = enterSum();
            }
        } else {
            System.out.println("Sorry, the sum should be numeric. ");
            sum = enterSum();
        }
        return sum;
    }
}