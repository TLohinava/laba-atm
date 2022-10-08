package com.solvd.atm.domain;

public enum CurrencyType {

    BYN("BYN"),
    RUB("RUB"),
    EUR("EUR"),
    USD("USD"),
    CNY("CNY");

    private String type;

    CurrencyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
