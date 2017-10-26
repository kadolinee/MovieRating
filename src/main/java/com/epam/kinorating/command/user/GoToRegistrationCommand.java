package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.PagePath;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        return PagePath.PAGE_REGISTRATION;
    }
}
