package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession(false).invalidate();

        return "controller?command=goToHome";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
