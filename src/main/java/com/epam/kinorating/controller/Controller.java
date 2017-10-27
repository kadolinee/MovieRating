package com.epam.kinorating.controller;

import com.epam.kinorating.command.ActionFactory;
import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Fields;
import com.epam.kinorating.config.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet{
    private final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page;
        try {
            Command command = ActionFactory.defineCommand(request.getParameter(Fields.COMMAND));
            page = command.execute(request);

            if (page == null) {
                command.execute(request, response);
            }

            if (page != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                page = PagePath.PAGE_INDEX;
                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (ServletException e) {
            log.error("Got a problem with servlet", e);
        } catch (IOException e) {
            log.error("Got a problem with IO in servlet", e);
        }
    }
}
