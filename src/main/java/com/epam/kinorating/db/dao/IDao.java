package com.epam.kinorating.db.dao;

public interface IDao<T> {

    void add(T model);

    void delete(int id);

    void update(T model);

    T get(int id);
}
