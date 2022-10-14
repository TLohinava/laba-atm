package com.solvd.atm;

import com.solvd.atm.domain.*;

import java.math.BigDecimal;
import java.util.*;

public class MainClass {

    public static void main(String[] args) {
        Address address1 = new Address();
        address1.setCity("Minsk");
        address1.setStreet("Kolasa");
        address1.setHouse(15);

        Address address2 = new Address();
        address2.setCity("Minsk");
        address2.setStreet("Kosmonavtov");
        address2.setHouse(32);

        Card card1 = new Card();
        card1.setNumber(5351402154561756L);
        card1.setPin(1234);
        card1.setBalance(BigDecimal.valueOf(350));
        card1.setCurrencyType(CurrencyType.valueOf("BYN"));

        Card card2 = new Card();
        card2.setNumber(5351402154234567L);
        card2.setPin(5678);
        card2.setBalance(BigDecimal.valueOf(9000));
        card2.setCurrencyType(CurrencyType.valueOf("BYN"));

        Card card3 = new Card();
        card3.setNumber(5351402154234888L);
        card3.setPin(8901);
        card3.setBalance(BigDecimal.valueOf(600));
        card3.setCurrencyType(CurrencyType.valueOf("BYN"));

        Card card4 = new Card();
        card4.setNumber(5351402154555567L);
        card4.setPin(1267);
        card4.setBalance(BigDecimal.valueOf(1200));
        card4.setCurrencyType(CurrencyType.valueOf("USD"));

        Card card5 = new Card();
        card5.setNumber(5351402144434567L);
        card5.setPin(4289);
        card5.setBalance(BigDecimal.valueOf(33000));
        card5.setCurrencyType(CurrencyType.valueOf("RUB"));

        Account account1 = new Account();
        account1.setNumber("BY06ALFA3012888877776666");
        List<Card> firstAccountCards = List.of(card1, card2);
        account1.setCards(firstAccountCards);

        Account account2 = new Account();
        account2.setNumber("BY04ALFA3014333555676789");
        List<Card> secondAccountCards = List.of(card3, card4, card5);
        account1.setCards(secondAccountCards);

        Client client1 = new Client();
        client1.setName("Dima");
        client1.setSurname("Orlov");
        client1.setAccount(account1);

        Client client2 = new Client();
        client2.setName("Ivan");
        client2.setSurname("Petrov");
        client2.setAccount(account2);

        Map<BigDecimal, BigDecimal> bynDenominations1 = new HashMap<>();
        bynDenominations1.put(BigDecimal.valueOf(5), BigDecimal.valueOf(100));
        bynDenominations1.put(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        bynDenominations1.put(BigDecimal.valueOf(20), BigDecimal.valueOf(100));
        bynDenominations1.put(BigDecimal.valueOf(50), BigDecimal.valueOf(100));
        bynDenominations1.put(BigDecimal.valueOf(100), BigDecimal.valueOf(100));

        Map<BigDecimal, BigDecimal> bynDenominations2 = new HashMap<>();
        bynDenominations2.put(BigDecimal.valueOf(5), BigDecimal.valueOf(50));
        bynDenominations2.put(BigDecimal.valueOf(10), BigDecimal.valueOf(50));
        bynDenominations2.put(BigDecimal.valueOf(20), BigDecimal.valueOf(100));
        bynDenominations2.put(BigDecimal.valueOf(50), BigDecimal.valueOf(50));
        bynDenominations2.put(BigDecimal.valueOf(100), BigDecimal.valueOf(100));

        Map<BigDecimal, BigDecimal> rubDenominations1 = new HashMap<>();
        rubDenominations1.put(BigDecimal.valueOf(50), BigDecimal.valueOf(100));
        rubDenominations1.put(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        rubDenominations1.put(BigDecimal.valueOf(200), BigDecimal.valueOf(10));
        rubDenominations1.put(BigDecimal.valueOf(500), BigDecimal.valueOf(10));
        rubDenominations1.put(BigDecimal.valueOf(1000), BigDecimal.valueOf(10));

        Map<BigDecimal, BigDecimal> rubDenominations2 = new HashMap<>();
        rubDenominations2.put(BigDecimal.valueOf(50), BigDecimal.valueOf(100));
        rubDenominations2.put(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        rubDenominations2.put(BigDecimal.valueOf(200), BigDecimal.valueOf(100));
        rubDenominations2.put(BigDecimal.valueOf(500), BigDecimal.valueOf(50));
        rubDenominations2.put(BigDecimal.valueOf(1000), BigDecimal.valueOf(50));

        Map<BigDecimal, BigDecimal> usdDenominations1 = new HashMap<>();
        usdDenominations1.put(BigDecimal.valueOf(5), BigDecimal.valueOf(50));
        usdDenominations1.put(BigDecimal.valueOf(10), BigDecimal.valueOf(50));
        usdDenominations1.put(BigDecimal.valueOf(20), BigDecimal.valueOf(50));
        usdDenominations1.put(BigDecimal.valueOf(50), BigDecimal.valueOf(10));
        usdDenominations1.put(BigDecimal.valueOf(100), BigDecimal.valueOf(10));

        Map<BigDecimal, BigDecimal> usdDenominations2 = new HashMap<>();
        usdDenominations2.put(BigDecimal.valueOf(5), BigDecimal.valueOf(60));
        usdDenominations2.put(BigDecimal.valueOf(10), BigDecimal.valueOf(100));
        usdDenominations2.put(BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        usdDenominations2.put(BigDecimal.valueOf(50), BigDecimal.valueOf(100));
        usdDenominations2.put(BigDecimal.valueOf(100), BigDecimal.valueOf(20));

        Map<BigDecimal, BigDecimal> eurDenominations1 = new HashMap<>();
        eurDenominations1.put(BigDecimal.valueOf(5), BigDecimal.valueOf(100));
        eurDenominations1.put(BigDecimal.valueOf(10), BigDecimal.valueOf(50));
        eurDenominations1.put(BigDecimal.valueOf(20), BigDecimal.valueOf(100));
        eurDenominations1.put(BigDecimal.valueOf(50), BigDecimal.valueOf(50));
        eurDenominations1.put(BigDecimal.valueOf(100), BigDecimal.valueOf(50));

        Atm atm1 = new Atm();
        atm1.setAddress(address1);
        Map<CurrencyType, Map<BigDecimal, BigDecimal>> firstAtmBalance = new HashMap<>();
        firstAtmBalance.put(CurrencyType.valueOf("BYN"), bynDenominations1);
        firstAtmBalance.put(CurrencyType.valueOf("RUB"), rubDenominations1);
        firstAtmBalance.put(CurrencyType.valueOf("USD"), usdDenominations1);
        firstAtmBalance.put(CurrencyType.valueOf("EUR"), eurDenominations1);
        atm1.setBalance(firstAtmBalance);

        Atm atm2 = new Atm();
        atm2.setAddress(address2);
        Map<CurrencyType, Map<BigDecimal, BigDecimal>> secondAtmBalance = new HashMap<>();
        secondAtmBalance.put(CurrencyType.valueOf("BYN"), bynDenominations2);
        secondAtmBalance.put(CurrencyType.valueOf("RUB"), rubDenominations2);
        secondAtmBalance.put(CurrencyType.valueOf("USD"), usdDenominations2);
        atm2.setBalance(secondAtmBalance);

        Bank alfaBank = new Bank();
        alfaBank.setName("Alfa Bank");
        List<Atm> alfaAtms = List.of(atm1, atm2);
        alfaBank.setAtms(alfaAtms);
        List<Client> alfaClients = List.of(client1, client2);
        alfaBank.setClients(alfaClients);

        Cash cash = new Cash();
        cash.setCurrencyType(CurrencyType.USD);
        cash.setDenomination(new BigDecimal(10));
        cash.setQuantity(new BigDecimal(8));

        Cash cash1 = new Cash();
        cash1.setCurrencyType(CurrencyType.USD);
        cash1.setDenomination(new BigDecimal(20));
        cash1.setQuantity(new BigDecimal(6));

        Cash cash2 = new Cash();
        cash2.setCurrencyType(CurrencyType.USD);
        cash2.setDenomination(new BigDecimal(50));
        cash2.setQuantity(new BigDecimal(3));

        List<Cash> cashList = List.of(cash, cash1, cash2);

//        CashService cs = new CashServiceImpl();
//        cs.updateBatch(cashList);

//        ClientService clientService = new ClientServiceImpl();
//        System.out.println(clientService.read(2L));

//        BankService bankService = new BankServiceImpl();
//        System.out.println(bankService.read(2L));

//        CashService cashService = new CashServiceImpl();
//        System.out.println(cashService.getMinBanknote(CurrencyType.RUB));

        Utils.selectFunction(atm2, card2);
    }
}