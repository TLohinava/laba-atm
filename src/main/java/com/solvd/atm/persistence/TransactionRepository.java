package com.solvd.atm.persistence;

import com.solvd.atm.domain.Transaction;
import org.apache.ibatis.annotations.Param;

public interface TransactionRepository {

    void create(@Param("atmId") Long atmId, @Param("cardId") Long cardId, @Param("transaction") Transaction transaction);

}
