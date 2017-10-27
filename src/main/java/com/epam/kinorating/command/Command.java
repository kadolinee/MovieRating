package com.epam.kinorating.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute(HttpServletRequest request);
    void execute(HttpServletRequest request, HttpServletResponse response);
}
