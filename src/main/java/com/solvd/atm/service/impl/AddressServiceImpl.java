package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Address;
import com.solvd.atm.domain.exception.QueryException;
import com.solvd.atm.persistence.AddressRepository;
import com.solvd.atm.persistence.impl.AddressMapperImpl;
import com.solvd.atm.service.AddressService;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl() {
        this.addressRepository = new AddressMapperImpl();
    }

    @Override
    public Address create(Address address) {
        address.setId(null);
        addressRepository.create(address);
        return address;
    }

    @Override
    public Address read(Long id) {
        return addressRepository.read(id)
                .orElseThrow(() -> new QueryException("No address found"));
    }

    @Override
    public void update(Address address) {
        addressRepository.update(address);
    }

    @Override
    public void delete(Long deleteId) {
        addressRepository.delete(deleteId);
    }
}
