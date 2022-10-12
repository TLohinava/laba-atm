package com.solvd.atm.domain;

import java.math.BigDecimal;

public class Cash {

    private Long id;
    private BigDecimal denomination;
    private BigDecimal quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}