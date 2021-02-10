package com.example.banksystem.repositories.interfaces;

import com.example.banksystem.models.History;

import java.util.List;

public interface IHistoryRepository {
    List<History> getHistoryOfWallet(int walletID);
}
