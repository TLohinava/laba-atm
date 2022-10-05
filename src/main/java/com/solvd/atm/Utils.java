package com.solvd.atm;

import com.solvd.atm.domain.*;

public class Utils {

    public static void useATM(IUseATM useATM, Card card) {
        useATM.getMenu(card);
    }
}