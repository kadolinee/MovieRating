package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

public class CheckNameCommand implements Command {
    private static final Logger log = Logger.getLogger(CheckNameCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        return null;
        //Do nothing because of I use another version of the overloaded method
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(Attribute.ATTRIBUTE_USERNAME);
        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            if (!UserService.isFree(userName, null)) {
                printWriter.print("false");
            }
        } catch (UnknownHostException e) {
            log.error("Got a problem with the host" ,e);
        } catch (IOException e) {
            log.error("Got a problem with IO", e);
        }

    }
}
