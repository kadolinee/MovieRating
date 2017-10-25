package com.epam.kinorating.service.impl;

import com.epam.kinorating.db.dao.MovieDAO;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.DAOException;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;

public class MovieServiceImpl implements MovieService {
    private static final Logger log = Logger.getLogger(MovieServiceImpl.class);

    private MovieDAO movieDAO;


    public MovieServiceImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public Movie find(int id) throws ServiceException {
        try {
            return movieDAO.read(id);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding movie", e);
            throw new ServiceException("Invalid service operation occurred during finding movie", e);
        }
    }

    @Override
    public boolean add(Movie movie) throws ServiceException {
        try {
            return movieDAO.create(movie);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during adding movie", e);
            throw new ServiceException("Invalid service operation occurred during adding movie", e);
        }
    }

    @Override
    public ArrayList<Movie> find(int page, String name) throws ServiceException {
        try {
            return movieDAO.read(page, name);
        } catch (DAOException e) {
            log.error("Invalid service operation occurred during finding all movies", e);
            throw new ServiceException("Invalid service operation occurred during finding all movies", e);
        }
    }
}
