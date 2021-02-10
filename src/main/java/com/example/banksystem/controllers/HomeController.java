package com.example.banksystem.controllers;

import com.example.banksystem.models.User;
import com.example.banksystem.repositories.ServiceRepository;
import com.example.banksystem.repositories.interfaces.IServicesRepository;
import com.example.banksystem.services.UserService;
import com.example.banksystem.services.WalletService;
import com.example.banksystem.services.interfaces.IUserService;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final IUserService userService;
    private final IServicesRepository servicesRepository;
    private final IWalletService walletService;

    @Autowired
    public HomeController(UserService userService, ServiceRepository servicesRepository, WalletService walletService) {
        this.userService = userService;
        this.servicesRepository = servicesRepository;
        this.walletService = walletService;
    }

    @RequestMapping("")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        String signedUserPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        User signedUser = this.userService.getUserByPhone(signedUserPhone);
        System.out.println("[HomeController] " + signedUser);
        model.addAttribute("signedUsed", signedUser);
        return "home";
    }

    @RequestMapping("/transfer")
    public String transfer(Model model) {
        String signedUserPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        User signedUser = this.userService.getUserByPhone(signedUserPhone);
        model.addAttribute("walletList", this.walletService.getWalletListByUserId(signedUser.getId()));
        return "transfer";
    }

    @RequestMapping("/service")
    public String service(Model model) {
        String signedUserPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        User signedUser = this.userService.getUserByPhone(signedUserPhone);
        System.out.println("[HomeController] " + signedUser);
        model.addAttribute("walletList", this.walletService.getWalletListByUserId(signedUser.getId()));
        model.addAttribute("services", this.servicesRepository.getServiceList());
        return "service";
    }
}
