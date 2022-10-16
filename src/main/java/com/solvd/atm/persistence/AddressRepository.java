package com.solvd.atm.persistence;

import com.solvd.atm.domain.Address;

import java.util.Optional;

public interface AddressRepository {

    void create(Address address);

    Optional<Address> read(Long id);

    void update(Address address);

    void delete(Long deleteId);

}