package com.solvd.atm.service;

import com.solvd.atm.domain.Client;

import java.util.Optional;

public interface ClientService {

    Client create(Long accountId, Client client);

    Optional<Client> read(Long id);

    void update(Client client);

    void deleteById(Long deleteId);

}
