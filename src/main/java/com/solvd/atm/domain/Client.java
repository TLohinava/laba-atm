package com.solvd.atm.domain;

import com.solvd.atm.Utils;

public class Client implements IUseATM {

    private Long id;
    private String name;
    private String surname;
    private Account account;

    @Override
    public void getMenu(Atm atm, Card card) {
        Utils.selectFunction(atm, card);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", account=" + account +
                '}';
    }
}