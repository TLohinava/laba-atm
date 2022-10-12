package com.solvd.atm.service;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.CurrencyType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface AtmService {

    Atm create(Long bankId, Long addressId, Atm atm);

    Optional<Atm> read(Long id);

    void update(Atm atm);

    void deleteById(Long deleteId);

}
