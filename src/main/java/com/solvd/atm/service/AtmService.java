package com.solvd.atm.service;

import com.solvd.atm.domain.Atm;

public interface AtmService {

    Atm create(Long bankId, Long addressId, Atm atm);

    Atm read(Long id);

    void update(Atm atm);

    void deleteById(Long deleteId);

}