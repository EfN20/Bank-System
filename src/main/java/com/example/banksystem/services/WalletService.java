package com.example.banksystem.services;

import com.example.banksystem.models.Wallet;
import com.example.banksystem.repositories.WalletRepository;
import com.example.banksystem.repositories.interfaces.IWalletRepository;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalletService implements IWalletService {
    private final IWalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void add(Wallet entity) {
        this.walletRepository.add(entity);
    }

    @Override
    @Transactional
    public void update(Wallet entity) {
        this.walletRepository.update(entity);
    }

    @Override
    @Transactional
    public void remove(int id) {
        this.walletRepository.remove(id);
    }

    @Override
    @Transactional
    public Wallet select(int id) {
        return this.walletRepository.select(id);
    }

    @Override
    @Transactional
    public List<Wallet> getWalletListByUserId(int userId) {
        return this.walletRepository.getWalletListByUserId(userId);
    }

    @Override
    @Transactional
    public Wallet getWalletBySerialNumber(String serialNumber) {
        return this.walletRepository.getWalletBySerialNumber(serialNumber);
    }
}
