package com.example.banksystem.repositories.interfaces;

import com.example.banksystem.models.User;

import java.util.List;

public interface IUserRepository extends IEntityRepository<User> {
    List<User> getUserList();

    User findUserByPhoneNumber(String phoneNumber);
}
