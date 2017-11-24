package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.db.dao.IDao;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class DaoImpl<T> implements IDao<T> {
    @PersistenceContext
    @Getter
    private EntityManager em;
    private final Class<T> clazz;

    protected DaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void add(T model) {
        em.persist(model);
    }

    public void delete(int id) {
        em.remove(get(id));
    }

    public T get(int id) {
        return em.find(clazz, id);
    }

    public void update(T model) {
        em.merge(model);
    }
}
