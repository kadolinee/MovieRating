package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.Movie;

import java.sql.Connection;
import java.util.ArrayList;

public interface MovieDAO {
    ArrayList<Movie> readByPage(int page);

    ArrayList<Movie> read(String name);

    boolean create(Movie movie);

    Movie read(int id);
}
