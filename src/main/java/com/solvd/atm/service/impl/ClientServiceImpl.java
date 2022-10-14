package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Client;
import com.solvd.atm.persistence.ClientRepository;
import com.solvd.atm.persistence.impl.ClientMapperImpl;
import com.solvd.atm.service.ClientService;

import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClientMapperImpl();
    }

    @Override
    public Client create(Long accountId, Client client) {
        client.setId(null);
        clientRepository.create(accountId, client);
        return client;
    }

    @Override
    public Optional<Client> read(Long id) {
        return clientRepository.read(id);
    }

    @Override
    public void update(Client client) {
        clientRepository.update(client);
    }

    @Override
    public void deleteById(Long deleteId) {
        clientRepository.deleteById(deleteId);
    }
}
