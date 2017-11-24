package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.Movie;
import org.hibernate.HibernateException;

import java.util.List;

public interface MovieDao {
    List<Movie> get(int page, String name) throws HibernateException;

}
