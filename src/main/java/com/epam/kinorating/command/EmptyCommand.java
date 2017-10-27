package com.epam.kinorating.command;

import com.epam.kinorating.config.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.PAGE_INDEX;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
