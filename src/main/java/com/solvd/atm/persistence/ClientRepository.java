package com.solvd.atm.persistence;

import com.solvd.atm.domain.Client;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface ClientRepository {

    void create(@Param("accountId") Long accountId, @Param("client") Client client);

    Optional<Client> read(Long id);

    void update(Client client);

    void deleteById(Long deleteId);

}