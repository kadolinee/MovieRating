package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.UserAction;

import java.sql.Connection;
import java.util.ArrayList;

public interface UserActionDAO {
    boolean create(UserAction userAction);

    ArrayList<UserAction> readAll(int movieId);

    int isRated(int userId, int movieId);
}
