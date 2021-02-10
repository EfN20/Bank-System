package com.example.banksystem.controllers;

import com.example.banksystem.models.User;
import com.example.banksystem.models.Wallet;
import com.example.banksystem.services.UserService;
import com.example.banksystem.services.WalletService;
import com.example.banksystem.services.interfaces.IUserService;
import com.example.banksystem.services.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final IWalletService walletService;
    private User signedUser;
    final long MAX_NUMBER_YOU_WANT_TO_HAVE = 9999999999999999L;
    final long MIN_NUMBER_YOU_WANT_TO_HAVE = 1000000000000000L;

    @Autowired
    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }

    @GetMapping("/list")
    public String UserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "user/allUsers";
    }

    @GetMapping("/{id}")
    public String select(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.select(id));
        model.addAttribute("walletList", walletService.getWalletListByUserId(id));
//        String signedUserPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        return "user/profile";
    }

    @GetMapping("/register")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "user/registration";
    }

    @PostMapping("/register")
    public String add(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "user/registration";
        }

        userService.add(user);
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) {
        User user = userService.select(id);
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User updatedUser, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "user/edit";
        }

        userService.update(updatedUser);
        return "index";
    }

    @GetMapping("/{id}/newWallet")
    public String newWallet(Model model, @PathVariable("id") int id) {
        Wallet wallet = new Wallet();
        Long actual = Long.valueOf(Math.abs(Float.valueOf(new Random().nextFloat() * (MAX_NUMBER_YOU_WANT_TO_HAVE - MIN_NUMBER_YOU_WANT_TO_HAVE)).longValue()));
        wallet.setUser(userService.select(id));
        wallet.setSerialNumber(String.valueOf(actual));
        System.out.println("[UserController] " + wallet.getUser().getId() + " " + wallet.getSerialNumber());
        System.out.println(wallet);
        this.walletService.add(wallet);
        model.addAttribute("user", userService.select(id));
        model.addAttribute("walletList", walletService.getWalletListByUserId(id));
        return "user/profile";
    }
}
