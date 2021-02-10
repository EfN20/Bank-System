package com.example.banksystem.services.interfaces;

import com.example.banksystem.models.Wallet;

import java.util.List;

public interface IWalletService {
    void add(Wallet entity);

    void update(Wallet entity);

    void remove(int id);

    Wallet select(int id);

    List<Wallet> getWalletListByUserId(int userId);

    Wallet getWalletBySerialNumber(String serialNumber);
}
