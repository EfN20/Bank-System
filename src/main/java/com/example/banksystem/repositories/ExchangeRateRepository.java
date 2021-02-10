package com.example.banksystem.repositories;

import com.example.banksystem.models.ExchangeRate;
import com.example.banksystem.models.User;
import com.example.banksystem.repositories.interfaces.IExchangeRateRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ExchangeRateRepository implements IExchangeRateRepository {
    private static Logger LOGGER = Logger.getLogger(ExchangeRateRepository.class.getName());
    private final SessionFactory sessionFactory;

    @Autowired
    public ExchangeRateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(ExchangeRate entity) {

    }

    @Override
    public void update(ExchangeRate entity) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public ExchangeRate select(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ExchangeRate exchangeRate = session.load(ExchangeRate.class, id);
        LOGGER.info("Exchange rate was selected successfully. Exchange rate -> " + exchangeRate);
        return exchangeRate;
    }

    @Override
    public List<ExchangeRate> getRateList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ExchangeRate> exchangeRateList = session.createQuery("from ExchangeRate").list();
        exchangeRateList.forEach(exchangeRate -> LOGGER.info("Exchange rate was selected successfully. Exchange rate -> " + exchangeRate));
        return exchangeRateList;
    }
}
