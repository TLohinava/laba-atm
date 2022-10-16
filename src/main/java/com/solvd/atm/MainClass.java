package com.solvd.atm;

import com.solvd.atm.domain.*;
import com.solvd.atm.service.*;
import com.solvd.atm.service.impl.*;

public class MainClass {

    public static void main(String[] args) {
        AtmService as = new AtmServiceImpl();
        Atm atm = as.read(1l);

        CardService cs = new CardServiceImpl();
        Card client = cs.read(2l);

        Utils.selectFunction(atm, client);
    }
}