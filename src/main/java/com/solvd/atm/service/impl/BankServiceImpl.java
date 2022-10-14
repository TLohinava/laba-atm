package com.solvd.atm.service.impl;

import com.solvd.atm.domain.Atm;
import com.solvd.atm.domain.Bank;
import com.solvd.atm.domain.Client;
import com.solvd.atm.persistence.BankRepository;
import com.solvd.atm.persistence.impl.BankMapperImpl;
import com.solvd.atm.service.AtmService;
import com.solvd.atm.service.BankService;
import com.solvd.atm.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final ClientService clientService;
    private final AtmService atmService;

    public BankServiceImpl() {
        this.bankRepository = new BankMapperImpl();
        this.clientService = new ClientServiceImpl();
        this.atmService = new AtmServiceImpl();
    }

    @Override
    public Bank create(Bank bank) {
        bank.setId(null);
        bankRepository.create(bank);

//        if (bank.getAtms() != null) {
//            List<Atm> atms = bank.getAtms().stream()
//                    .map(atm -> atmService.create(bank.getId(), atm.getAddress().getId(), atm))
//                    .collect(Collectors.toList());
//            bank.setAtms(atms);
//        }
//
//        if (bank.getClients() != null) {
//            List<Client> clients = bank.getClients().stream()
//                    .map(client -> clientService.create(client.getAccount().getId(), client))
//                    .collect(Collectors.toList());
//            bank.setClients(clients);
//        }
//        bank.getClients().forEach(client -> bankRepository.createClientConnection(bank, client));

        return bank;
    }

    @Override
    public Optional<Bank> read(Long id) {
        return bankRepository.read(id);
    }

    @Override
    public void update(Bank bank) {
        bankRepository.update(bank);
    }

    @Override
    public void deleteById(Long deleteId) {
        bankRepository.deleteById(deleteId);
    }
}
