package com.solvd.atm.persistence;

import com.solvd.atm.domain.Atm;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface AtmRepository {

    void create(@Param("bankId") Long bankId, @Param("addressId") Long addressId, @Param("atm") Atm atm);

    Optional<Atm> read(Long id);

    void update(Atm atm);

    void deleteById(Long deleteId);

}