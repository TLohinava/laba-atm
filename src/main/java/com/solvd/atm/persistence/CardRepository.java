package com.solvd.atm.persistence;

import com.solvd.atm.domain.Card;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface CardRepository {

    void create(@Param("accountId") Long accountId, @Param("card") Card card);

    Optional<Card> read(Long id);

    void update(Card card);

    void deleteById(Long deleteId);

}