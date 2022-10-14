package com.solvd.atm.service;

import com.solvd.atm.domain.Address;

public interface AddressService {

    Address create(Address address);

    Address read(Long id);

    void update(Address address);

    void delete(Long deleteId);

}
