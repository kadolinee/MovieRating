package com.epam.kinorating.command;

import com.epam.kinorating.config.PagePath;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        return PagePath.PAGE_INDEX;
    }
}
