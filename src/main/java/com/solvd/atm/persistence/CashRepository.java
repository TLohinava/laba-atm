package com.solvd.atm.persistence;

import com.solvd.atm.domain.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.*;

public interface CashRepository {

    List<Cash> read(Long atmId);

    Optional<Cash> readQuantity(@Param("atmId") Long atmId, @Param("currencyType") CurrencyType currencyType, @Param("denomination") BigDecimal denomination);

    void create(@Param("atmId") Long atmId, @Param("list") List<Cash> cashList);

    void update(Cash cash);

    void delete();

}