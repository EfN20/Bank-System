package com.example.banksystem.services.interfaces;

import com.example.banksystem.models.User;

import java.util.List;

public interface IUserService {
    void add(User entity);

    void update(User entity);

    void remove(int id);

    User select(int id);

    List<User> getUserList();

    public User getUserByPhone(String phoneNumber);
}
