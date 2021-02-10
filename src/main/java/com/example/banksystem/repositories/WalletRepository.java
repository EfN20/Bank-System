package com.example.banksystem.repositories;

import com.example.banksystem.filters.MyLogger;
import com.example.banksystem.models.User;
import com.example.banksystem.models.Wallet;
import com.example.banksystem.repositories.interfaces.IUserRepository;
import com.example.banksystem.repositories.interfaces.IWalletRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Repository
public class WalletRepository implements IWalletRepository {
//    private static Logger LOGGER = Logger.getLogger(WalletRepository.class.getName());
    private final SessionFactory sessionFactory;
    private IUserRepository userRepository;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public WalletRepository(SessionFactory sessionFactory, UserRepository userRepository) {
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }

    @Override
    public void add(Wallet entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(entity);
//        LOGGER.info("Wallet was saved successfully. Wallet -> " + entity);
        executorService.submit(new MyLogger(WalletRepository.class.getName(), "Wallet was saved successfully. Wallet -> " + entity));
    }

    @Override
    public void update(Wallet entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(entity);
//        LOGGER.info("Wallet was updated successfully. Wallet -> " + entity);
        executorService.submit(new MyLogger(WalletRepository.class.getName(), "Wallet was updated successfully. Wallet -> " + entity));
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Wallet toRemove = session.load(Wallet.class, id);
        if (toRemove != null) {
            session.delete(toRemove);
        }
//        LOGGER.info("Wallet was deleted successfully. Wallet -> " + toRemove);
        executorService.submit(new MyLogger(WalletRepository.class.getName(), "Wallet was deleted successfully. Wallet -> " + toRemove));
    }

    @Override
    public Wallet select(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Wallet wallet = session.load(Wallet.class, id);
//        LOGGER.info("Wallet was selected successfully. Wallet -> " + wallet);
        executorService.submit(new MyLogger(WalletRepository.class.getName(), "Wallet was selected successfully. Wallet -> " + wallet));
        return wallet;
    }

    @Override
    public List<Wallet> getWalletListByUserId(int userId) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = this.userRepository.select(userId);
        List<Wallet> walletList = session.createQuery("from Wallet where user=:user").setParameter("user", user).list();
//        walletList.forEach(wallet -> LOGGER.info("Wallet list was selected successfully. Waller -> " + wallet));
        walletList.forEach(wallet -> executorService.submit(new MyLogger(WalletRepository.class.getName(),
                "Wallet list was selected successfully. Waller -> " + wallet)));

        return walletList;
    }

    @Override
    public Wallet getWalletBySerialNumber(String serialNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        Wallet wallet = (Wallet) session.createQuery("from Wallet where serialNumber=:serialNumber").
                            setParameter("serialNumber", serialNumber).uniqueResult();
//        LOGGER.info("Wallet was selected by serial number successfully. Wallet -> " + wallet);
        executorService.submit(new MyLogger(WalletRepository.class.getName(), "Wallet was selected by serial number successfully. Wallet -> " + wallet));
        return wallet;
    }
}
