package com.example.banksystem.controllers;

import com.example.banksystem.services.ExchangeRateService;
import com.example.banksystem.services.WalletService;
import com.example.banksystem.services.interfaces.IExchangeRateService;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {
    private final IExchangeRateService exchangeRateService;
    private final IWalletService walletService;

    @Autowired
    public ExchangeController(ExchangeRateService exchangeRateService, WalletService walletService) {
        this.exchangeRateService = exchangeRateService;
        this.walletService = walletService;
    }

    @GetMapping("/{id}")
    public String exchange(@PathVariable("id") int id, Model model) {
        model.addAttribute("wallet", walletService.select(id));
        model.addAttribute("rateList", exchangeRateService.getRateList());
        return "exchange";
    }
}
