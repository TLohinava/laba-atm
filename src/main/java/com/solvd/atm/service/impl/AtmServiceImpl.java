package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.persistence.AtmRepository;
import com.solvd.atm.persistence.impl.AtmMapperImpl;
import com.solvd.atm.service.AtmService;

public class AtmServiceImpl implements AtmService {

    private final AtmRepository atmRepository;

    public AtmServiceImpl() {
        this.atmRepository = new AtmMapperImpl();
    }

    @Override
    public Atm create(Long bankId, Long addressId, Atm atm) {
        atm.setId(null);
        atmRepository.create(bankId, addressId, atm);
        return atm;
    }

    @Override
    public Atm read(Long id) {
        return atmRepository.read(id)
                        .orElseThrow();
    }

    @Override
    public void update(Atm atm) {
        atmRepository.update(atm);
    }

    @Override
    public void deleteById(Long deleteId) {
        atmRepository.deleteById(deleteId);
    }
}