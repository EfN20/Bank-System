package com.example.banksystem.services;

import com.example.banksystem.models.ExchangeRate;
import com.example.banksystem.repositories.ExchangeRateRepository;
import com.example.banksystem.repositories.interfaces.IExchangeRateRepository;
import com.example.banksystem.services.interfaces.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExchangeRateService implements IExchangeRateService {
    private final IExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Transactional
    public ExchangeRate select(int id) {
        return this.exchangeRateRepository.select(id);
    }

    @Override
    @Transactional
    public List<ExchangeRate> getRateList() {
        return this.exchangeRateRepository.getRateList();
    }
}
