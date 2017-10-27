package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToErrorCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_ERROR;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
