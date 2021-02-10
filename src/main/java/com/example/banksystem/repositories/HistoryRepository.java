package com.example.banksystem.repositories;

import com.example.banksystem.filters.MyLogger;
import com.example.banksystem.models.History;
import com.example.banksystem.repositories.interfaces.IHistoryRepository;
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
public class HistoryRepository implements IHistoryRepository {
    private static Logger LOGGER = Logger.getLogger(HistoryRepository.class.getName());
    private final SessionFactory sessionFactory;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public HistoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<History> getHistoryOfWallet(int walletID) {
        Session session = this.sessionFactory.getCurrentSession();
        List<History> historyList = session.createQuery("from History where wallet_id=:walletId").setParameter("walletId", walletID).list();
//        historyList.forEach(history -> LOGGER.info("History list by wallet_id was selected successfully. History -> " + history));
        historyList.forEach(history -> executorService.submit(new MyLogger(HistoryRepository.class.getName(),
                "History list by wallet_id was selected successfully. History -> " + history)));
        return historyList;
    }
}
