package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Client;
import com.solvd.atm.persistence.ClientRepository;
import com.solvd.atm.persistence.MyBatisConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class ClientMapperImpl implements ClientRepository {

    @Override
    public void create(Long accountId, Client client) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.create(accountId, client);
        }
    }

    @Override
    public Optional<Client> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.read(id);
        }
    }

    @Override
    public void update(Client client) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.update(client);
        }
    }

    @Override
    public void deleteById(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.deleteById(deleteId);
        }
    }
}
