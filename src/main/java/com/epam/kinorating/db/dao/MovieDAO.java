package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.Movie;

import java.util.List;

public interface MovieDAO {
    boolean create(Movie movie);
    Movie read(int id);
    List<Movie> read(int page, String name);

}
