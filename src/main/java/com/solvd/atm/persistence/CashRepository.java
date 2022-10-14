package com.solvd.atm.persistence;

import com.solvd.atm.domain.Cash;
import com.solvd.atm.domain.CurrencyType;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CashRepository {

    Map<CurrencyType, Map<BigDecimal, BigDecimal>> getMap();

    List<Cash> read();

    void create(@Param("atmId") Long atmId, @Param("list") List<Cash> cashList);

    void update(Cash cash);

    void updateBatch(List<Cash> cashList);

}
