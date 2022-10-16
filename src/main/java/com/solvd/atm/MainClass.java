package com.solvd.atm;

import com.solvd.atm.domain.*;
import com.solvd.atm.service.*;
import com.solvd.atm.service.impl.*;

public class MainClass {

    public static void main(String[] args) {
        AtmService as = new AtmServiceImpl();
        Atm atm = as.read(1l);

        CardService cs = new CardServiceImpl();
        Card card = cs.read(2l);

        ClientService clientService = new ClientServiceImpl();
        Client client = clientService.read(1l);

        Utils.selectFunction(atm, card);

//        Thread thread1 = new Thread(Utils.synchronizeObjects(client, atm, card));
//        thread1.start();

    }
}