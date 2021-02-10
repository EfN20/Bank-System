package com.example.banksystem.controllers;

import com.example.banksystem.models.Wallet;
import com.example.banksystem.services.interfaces.IExchangeRateService;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/wallet")
public class WalletRest {
    private final IExchangeRateService exchangeRateService;
    private final IWalletService walletService;

    @Autowired
    public WalletRest(IExchangeRateService exchangeRateService, IWalletService walletService) {
        this.exchangeRateService = exchangeRateService;
        this.walletService = walletService;
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Wallet newWallet, @PathVariable("id") int id) {
        System.out.println("[WALLET REST] " + newWallet);
        Wallet oldWallet = walletService.select(id);
        oldWallet.setBalance_kzt(newWallet.getBalance_kzt());
        oldWallet.setBalance_usd(newWallet.getBalance_usd());
        oldWallet.setBalance_eur(newWallet.getBalance_eur());
        walletService.update(oldWallet);
//        return "Exchanged successfully";
//        return oldWallet;
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"response\": \"Successfully exchanged\"}");
    }

    @PostMapping(value = "/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> transfer(@RequestBody Map<String, String> json, @PathVariable("id") int id, @PathVariable("amount") Double amount) {
        String receiverSerialNumber = json.get("receiver");
        Wallet receiver;
        if(Long.parseLong(receiverSerialNumber) < 9999) {
            receiver = this.walletService.select(Integer.parseInt(receiverSerialNumber));
        }
        else if(!receiverSerialNumber.equals("1111333355557777")){
            receiver = this.walletService.getWalletBySerialNumber(receiverSerialNumber);
        }
        else{
            receiver = null;
        }
        String currency = json.get("currency");
        Wallet sender = this.walletService.select(id);

        if(receiver != null){
            System.out.println("[WalletRest] RECEIVER FROM SAME BANK");
            Double origin = amount;
            if(amount >= 100000){
                amount *= 0.99;
            }
            switch(currency){
                case "KZT":
                    sender.setBalance_kzt(((int) (sender.getBalance_kzt() - origin)));
                    receiver.setBalance_kzt((int) (receiver.getBalance_kzt() + amount));
                    break;

                case "USD":
                    sender.setBalance_usd(sender.getBalance_usd() - origin);
                    receiver.setBalance_usd(receiver.getBalance_usd() + amount);
                    break;

                case "EUR":
                    sender.setBalance_eur(sender.getBalance_eur() - origin);
                    receiver.setBalance_eur(receiver.getBalance_eur() + amount);
                    break;
            }
            this.walletService.update(sender);
            this.walletService.update(receiver);
        }
        else{
            System.out.println("[WalletRest] RECEIVER FROM ANOTHER BANK");
            amount *= 1.01;
            switch(currency){
                case "KZT":
                    sender.setBalance_kzt((int) (sender.getBalance_kzt() - amount));
                    break;

                case "USD":
                    sender.setBalance_usd(sender.getBalance_usd() - amount);
                    break;

                case "EUR":
                    sender.setBalance_eur(sender.getBalance_eur() - amount);
                    break;
            }
            this.walletService.update(sender);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"response\": \"Successfully transferred\"}");
    }

    /*@PostMapping(value = "/{id}/{amount}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> transferSameCard(@RequestBody Map<String, String> json, @PathVariable("id") int id, @PathVariable("amount") Double amount) {
        int receiverWalletId = Integer.parseInt(json.get("receiver"));
        Wallet receiver = this.walletService.select(receiverWalletId);
        String currency = json.get("currency");
        Wallet sender = this.walletService.select(id);

        switch(currency){
            case "KZT":
                sender.setBalance_kzt(((int) (sender.getBalance_kzt() - amount)));
                receiver.setBalance_kzt((int) (sender.getBalance_kzt() + amount));
                break;

            case "USD":
                sender.setBalance_usd(sender.getBalance_usd() - amount);
                receiver.setBalance_usd(sender.getBalance_usd() + amount);
                break;

            case "EUR":
                sender.setBalance_eur(sender.getBalance_eur() - amount);
                receiver.setBalance_eur(sender.getBalance_eur() + amount);
                break;
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"response\": \"Successfully transferred\"");
    }*/
}
