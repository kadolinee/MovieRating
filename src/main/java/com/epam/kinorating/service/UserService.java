package com.epam.kinorating.service;

import com.epam.kinorating.entity.User;

import java.util.ArrayList;

public interface UserService {
    User find(String name, String password);
    int add(User user);
    boolean update(User user);
    ArrayList<User> findAll();
    boolean isFreeMail(String mail);
    boolean isFreeName(String name);
    double calc(int ratingsNumber, double movieRating, int setRatingByUser);
}
