package com.example.banksystem.repositories.interfaces;

public interface IEntityRepository<T> {
    void add(T entity);

    void update(T entity);

    void remove(int id);

    T select(int id);
}
