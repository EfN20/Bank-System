package com.example.banksystem.controllers;

import com.example.banksystem.repositories.interfaces.IHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
    private IHistoryRepository historyRepository;

    @Autowired
    public HistoryController(IHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @GetMapping("/{id}")
    public String historyOfWallet(@PathVariable("id") int id, Model model) {
        model.addAttribute("historyList", this.historyRepository.getHistoryOfWallet(id));
        return "history";
    }
}
