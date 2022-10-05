package com.solvd.atm;

import com.solvd.atm.domain.*;
import com.solvd.atm.service.AccountService;
import com.solvd.atm.service.impl.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainClass {

    private static final Logger LOGGER = LogManager.getLogger(MainClass.class);

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
        card1.setNumber(535140215456175656L);
        card1.setBalance(BigDecimal.valueOf(350));
        card1.setCurrencyType(CurrencyType.valueOf("EUR"));

        Card card2 = new Card();
        card2.setNumber(5351402154234567L);
        card2.setBalance(BigDecimal.valueOf(9000));
        card2.setCurrencyType(CurrencyType.valueOf("BYN"));

        Card card3 = new Card();
        card3.setNumber(5351402154234888L);
        card3.setBalance(BigDecimal.valueOf(600));
        card3.setCurrencyType(CurrencyType.valueOf("BYN"));

        Card card4 = new Card();
        card4.setNumber(5351402154555567L);
        card4.setBalance(BigDecimal.valueOf(1200));
        card4.setCurrencyType(CurrencyType.valueOf("USD"));

        Card card5 = new Card();
        card5.setNumber(5351402144434567L);
        card5.setBalance(BigDecimal.valueOf(33000));
        card5.setCurrencyType(CurrencyType.valueOf("RUB"));

        Account account1 = new Account();
        account1.setNumber("BY06ALFA30128888777766665555");
        List<Card> firstAccountCards = List.of(card1, card2);
        account1.setCards(firstAccountCards);

        Account account2 = new Account();
        account2.setNumber("BY04ALFA301433355567678989");
        List<Card> secondAccountCards = List.of(card3, card4, card5);
        account1.setCards(secondAccountCards);

        Client client1 = new Client();
        client1.setName("Olga");
        client1.setSurname("Orlova");
        client1.setAccount(account1);

        Client client2 = new Client();
        client2.setName("Ivan");
        client2.setSurname("Petrov");
        client2.setAccount(account2);

        Atm atm1 = new Atm();
        atm1.setAddress(address1);
        Map<CurrencyType, BigDecimal> firstAtmBalance = new HashMap<>();
        firstAtmBalance.put(CurrencyType.valueOf("BYN"), BigDecimal.valueOf(10000));
        firstAtmBalance.put(CurrencyType.valueOf("RUB"), BigDecimal.valueOf(300000));
        firstAtmBalance.put(CurrencyType.valueOf("USD"), BigDecimal.valueOf(22000));
        firstAtmBalance.put(CurrencyType.valueOf("EUR"), BigDecimal.valueOf(14000));
        atm1.setBalance(firstAtmBalance);

        Atm atm2 = new Atm();
        atm1.setAddress(address2);
        Map<CurrencyType, BigDecimal> secondAtmBalance = new HashMap<>();
        secondAtmBalance.put(CurrencyType.valueOf("BYN"), BigDecimal.valueOf(200000));
        secondAtmBalance.put(CurrencyType.valueOf("RUB"), BigDecimal.valueOf(100000));
        secondAtmBalance.put(CurrencyType.valueOf("USD"), BigDecimal.valueOf(5000));
        atm2.setBalance(secondAtmBalance);

        Bank alfaBank = new Bank();
        alfaBank.setName("Alfa Bank");
        List<Atm> alfaAtms = List.of(atm1, atm2);
        alfaBank.setAtms(alfaAtms);
        List<Client> alfaClients = List.of(client1, client2);
        alfaBank.setClients(alfaClients);

        AccountService accountService = new AccountServiceImpl();
        accountService.create(account1);
    }
}
