package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        request.getSession(false).invalidate();

        return "controller?command=goToHome";
    }
}
