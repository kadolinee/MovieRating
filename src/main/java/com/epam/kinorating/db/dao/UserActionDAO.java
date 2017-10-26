package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.UserAction;

import java.util.List;

public interface UserActionDAO {
    boolean create(UserAction userAction);
    List<UserAction> readAll(int movieId);
    int isRated(int userId, int movieId);

}
