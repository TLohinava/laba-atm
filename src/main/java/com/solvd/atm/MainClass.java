package com.solvd.atm;

import com.solvd.atm.domain.*;
import com.solvd.atm.service.*;
import com.solvd.atm.service.impl.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class MainClass {

    public static void main(String[] args) {
        AtmService as = new AtmServiceImpl();
        Atm atm = as.read(1l);

        CardService cs = new CardServiceImpl();
        Card client = cs.read(2l);

        Utils.selectFunction(atm, client);

//        TransactionService transactionService = new TransactionServiceImpl();
//        transactionService.create(1L, 1L, transaction);

        Thread thread1 = new Thread(Utils.synchronizeObjects(client1, atm1, card1));
        thread1.start();

    }
}