package com.example.banksystem.repositories.interfaces;

import com.example.banksystem.models.Service;

import java.util.List;

public interface IServicesRepository {
    List<Service> getServiceList();
}
