package com.solvd.atm.persistence.impl;

import com.solvd.atm.domain.Address;
import com.solvd.atm.persistence.*;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class AddressMapperImpl implements AddressRepository {

    @Override
    public void create(Address address) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.create(address);
        }
    }

    @Override
    public Optional<Address> read(Long id) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            return addressRepository.read(id);
        }
    }

    @Override
    public void update(Address address) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.update(address);
        }
    }

    @Override
    public void delete(Long deleteId) {
        try (SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            AddressRepository addressRepository = sqlSession.getMapper(AddressRepository.class);
            addressRepository.delete(deleteId);
        }
    }
}