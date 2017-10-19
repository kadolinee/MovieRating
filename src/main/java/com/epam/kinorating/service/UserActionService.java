package com.epam.kinorating.service;

import com.epam.kinorating.entity.UserAction;

import java.util.Map;

public interface UserActionService {
    boolean add(UserAction userAction);
    Map<String, Object> getMovieDetails(int movieId);
    boolean isRated(int userId, int movieId);
}
