package com.example.banksystem.services.interfaces;

import com.example.banksystem.models.ExchangeRate;

import java.util.List;

public interface IExchangeRateService {
    ExchangeRate select(int id);

    List<ExchangeRate> getRateList();
}
