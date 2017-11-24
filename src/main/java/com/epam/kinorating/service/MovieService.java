package com.epam.kinorating.service;

import com.epam.kinorating.db.dao.MovieDao;
import com.epam.kinorating.db.dao.impl.MovieDaoImpl;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class MovieService {
    private static final Logger log = Logger.getLogger(MovieService.class);

    private static MovieDao movieDAO = new MovieDaoImpl();

    private MovieService() {}

    public static Movie find(int id) {
        try {
            return movieDAO.read(id);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding movie", e);
            throw new ServiceException("Invalid service operation occurred during finding movie", e);
        }
    }

    public static boolean add(Movie movie) {
        try {
            return movieDAO.create(movie);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding movie", e);
            throw new ServiceException("Invalid service operation occurred during adding movie", e);
        }
    }

    public static List<Movie> find(int page, String name) {
        try {
            return movieDAO.read(page, name);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding all movies", e);
            throw new ServiceException("Invalid service operation occurred during finding all movies", e);
        }
    }
}
