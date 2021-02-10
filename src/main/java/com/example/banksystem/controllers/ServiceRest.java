package com.example.banksystem.controllers;

import com.example.banksystem.models.Wallet;
import com.example.banksystem.repositories.ServiceRepository;
import com.example.banksystem.repositories.interfaces.IServicesRepository;
import com.example.banksystem.services.WalletService;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/payService")
public class ServiceRest {
    private final IServicesRepository servicesRepository;
    private final IWalletService walletService;

    @Autowired
    public ServiceRest(ServiceRepository servicesRepository, WalletService walletService) {
        this.servicesRepository = servicesRepository;
        this.walletService = walletService;
    }

    @PostMapping(value = "/{id}/{price}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String payService(@PathVariable("price") int price, @PathVariable("id") int id) {
        Wallet wallet = this.walletService.select(id);
        wallet.setBalance_kzt(wallet.getBalance_kzt() - price);
        this.walletService.update(wallet);
//        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);
        return "{\"response\": \"Successfully paid\"}";
    }
}
