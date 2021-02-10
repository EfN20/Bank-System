package com.example.banksystem.repositories.interfaces;

import com.example.banksystem.models.Wallet;

import java.util.List;

public interface IWalletRepository extends IEntityRepository<Wallet> {
    List<Wallet> getWalletListByUserId(int userId);

    Wallet getWalletBySerialNumber(String serialNumber);
}
