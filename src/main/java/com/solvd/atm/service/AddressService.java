package com.solvd.atm.service;

import com.solvd.atm.domain.Address;

import java.util.Optional;

public interface AddressService {

    Address create(Address address);

    Optional<Address> read(Long id);

    void update(Address address);

    void delete(Long deleteId);

}
