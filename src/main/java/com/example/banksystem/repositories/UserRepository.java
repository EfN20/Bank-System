package com.example.banksystem.repositories;

import com.example.banksystem.filters.MyLogger;
import com.example.banksystem.models.User;
import com.example.banksystem.repositories.interfaces.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class UserRepository implements IUserRepository {
//    private static MyLogger LOGGER;
    private final SessionFactory sessionFactory;
    private final PasswordEncoder passwordEncoder;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public UserRepository(SessionFactory sessionFactory, PasswordEncoder passwordEncoder) {
        this.sessionFactory = sessionFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        session.persist(entity);
        System.out.println("Not logger" + entity);
//        LOGGER.info("User was inserted successfully. User -> " + entity);
        executorService.submit(new MyLogger(UserRepository.class.getName(), "User was inserted successfully. User -> " + entity));
    }

    @Override
    public void update(User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        session.update(entity);
//        LOGGER.info("User was updated successfully. User -> " + entity);
        executorService.submit(new MyLogger(UserRepository.class.getName(), "User was updated successfully. User -> " + entity));
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User toRemove = session.load(User.class, id);
        if (toRemove != null) {
            session.delete(toRemove);
        }
//        LOGGER.info("User was deleted successfully. User -> " + toRemove);
        executorService.submit(new MyLogger(UserRepository.class.getName(), "User was deleted successfully. User -> " + toRemove));
    }

    @Override
    public User select(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
//        LOGGER.info("User was selected successfully. User -> " + user);
        executorService.submit(new MyLogger(UserRepository.class.getName(), "User was selected successfully. User -> " + user));
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserList() {
        Session session = this.sessionFactory.getCurrentSession();
        @SuppressWarnings("JpaQlInspection") List<User> userList = session.createQuery("from User").list();
//        userList.forEach(user -> LOGGER.info("User list was successfully selected. User -> " + user));
        userList.forEach(user -> {
            executorService.submit(new MyLogger(UserRepository.class.getName(), "User list was successfully selected. User -> " + user));
        });
        return userList;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User where phone=:phone").setParameter("phone", phoneNumber).uniqueResult();
//        LOGGER.info("Unique User by phone number was selected successfully. User -> " + user);
        executorService.submit(new MyLogger(UserRepository.class.getName(), "Unique User by phone number was selected successfully. User -> " + user));
        return user;
    }
}
