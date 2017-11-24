package com.epam.kinorating.db.dao.impl;

import com.epam.kinorating.db.dao.MovieDao;
import com.epam.kinorating.entity.Movie;
import org.hibernate.HibernateException;

import javax.persistence.Query;
import java.util.List;

public class MovieDaoImpl extends DaoImpl<Movie> implements MovieDao {

    public MovieDaoImpl() {
        super(Movie.class);
    }

    private static final String SQL_READ_MOVIES_FOR_PAGE = "";
    private static final String SQL_READ_MOVIES_BY_NAME = "";

    public List<Movie> get(int page, String name) throws HibernateException {
        Query query;
        List<Movie> movieList;
        if (name == null) {
            query = getEm().createQuery("SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie ORDER BY id DESC LIMIT ?, 3"); //!!!!!!!!!!!
            movieList = query.getResultList();
        } else {
            query = getEm().createQuery("SELECT id, name_ru, name_en, title_ru, title_en, image FROM movie WHERE UPPER (name_ru) = UPPER (?) or UPPER (name_en) = UPPER (?)");
            movieList = query.getResultList();
        }

        return movieList;
    }
}
