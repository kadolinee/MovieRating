package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.User;

import java.util.List;

public interface UserDAO {
    boolean update(User user);

    int check(String name, String mail);

    String read(String name);

    User read(String name, String password);

    int create(User user);

    List<User> readAll();
}
