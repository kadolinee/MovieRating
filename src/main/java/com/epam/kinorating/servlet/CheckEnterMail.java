package com.epam.kinorating.servlet;

import com.epam.kinorating.db.dao.impl.UserDAOImpl;
import com.epam.kinorating.service.UserService;
import com.epam.kinorating.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "checkmail", urlPatterns = "/checkmail")
public class CheckEnterMail extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String name = null;
        PrintWriter printWriter = response.getWriter();

        if (!userService.isFree(name, mail)) {
            printWriter.print("false");
        }
    }
}
