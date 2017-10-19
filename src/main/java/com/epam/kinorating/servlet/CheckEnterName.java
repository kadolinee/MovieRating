package com.epam.kinorating.servlet;

import com.epam.kinorating.db.dao.impl.UserDAOImpl;
import com.epam.kinorating.service.UserService;
import com.epam.kinorating.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "checkname", urlPatterns = "/checkname")
public class CheckEnterName extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserDAOImpl());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");

        PrintWriter printWriter = response.getWriter();

        if (!userService.isFreeName(userName)) {
            printWriter.print("false");
        }
    }
}
