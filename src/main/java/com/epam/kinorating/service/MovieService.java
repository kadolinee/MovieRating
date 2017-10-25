package com.epam.kinorating.service;

import com.epam.kinorating.entity.Movie;

import java.util.ArrayList;

public interface MovieService {
    ArrayList<Movie> find(int page, String name);
    Movie find(int id);
    boolean add(Movie movie);

}
