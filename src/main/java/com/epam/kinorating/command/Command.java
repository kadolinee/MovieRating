package com.epam.kinorating.command;

import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                   UserActionService userActionService);
}
