package com.solvd.atm.service;

import com.solvd.atm.domain.Client;

public interface ClientService {

    Client create(Long accountId, Client client);

    Client read(Long id);

    void update(Client client);

    void deleteById(Long deleteId);

}
