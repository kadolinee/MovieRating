package com.epam.kinorating.db.dao;

import com.epam.kinorating.entity.User;

import java.util.List;

public interface UserDAO {
    int create(User user);
    User read(String name, String password);
    String read(String name);
    int check(String name, String mail);
    boolean update(User user);
    List<User> readAll();
}
