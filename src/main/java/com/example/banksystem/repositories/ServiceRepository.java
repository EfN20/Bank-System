package com.example.banksystem.repositories;

import com.example.banksystem.filters.MyLogger;
import com.example.banksystem.models.Service;
import com.example.banksystem.repositories.interfaces.IServicesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Repository
public class ServiceRepository implements IServicesRepository {
    private static Logger LOGGER = Logger.getLogger(ServiceRepository.class.getName());
    private final SessionFactory sessionFactory;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public ServiceRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Service> getServiceList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Service> serviceList = session.createQuery("from Service").list();
//        serviceList.forEach(service -> LOGGER.info("Service list was selected successfully. Service -> " + service));
        serviceList.forEach(service -> executorService.submit(new MyLogger(ServiceRepository.class.getName(),
                "Service list was selected successfully. Service -> " + service)));
        return serviceList;
    }
}
