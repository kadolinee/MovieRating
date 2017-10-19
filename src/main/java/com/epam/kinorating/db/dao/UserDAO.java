package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.User;

import java.sql.Connection;
import java.util.ArrayList;

public interface UserDAO {
    boolean update(User user);

    int checkName(String name);

    int checkMail(String mail);

    String read(String name);

    User read(String name, String password);

    int create(User user);

    ArrayList<User> readAll();
}
