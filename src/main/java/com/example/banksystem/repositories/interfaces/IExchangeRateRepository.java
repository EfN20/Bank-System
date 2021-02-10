package com.example.banksystem.repositories.interfaces;

import com.example.banksystem.models.ExchangeRate;

import java.util.List;

public interface IExchangeRateRepository extends IEntityRepository<ExchangeRate> {
    List<ExchangeRate> getRateList();
}
