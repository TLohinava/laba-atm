package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.CurrencyType;
import com.solvd.atm.persistence.AtmRepository;
import com.solvd.atm.persistence.impl.AtmMapperImpl;
import com.solvd.atm.service.AtmService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

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
    public Optional<Atm> read(Long id) {
        return atmRepository.read(id);
    }

    @Override
    public Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap() {
        return atmRepository.getMap();
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
