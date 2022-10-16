package com.solvd.atm.service;

import com.solvd.atm.domain.*;

import java.math.BigDecimal;
import java.util.*;


public interface CashService {

    List<Cash> read(Long atmId);

    Cash readQuantity(Long atmId, CurrencyType currencyType, BigDecimal denomination);

    void create(Long atmId, List<Cash> cashList);

    void update(Cash cash);

    void delete();

}