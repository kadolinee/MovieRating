package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegistrationCommand implements Command {
    private final Logger log = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        String userName = request.getParameter(Attribute.ATTRIBUTE_USERNAME);
        String password = request.getParameter(Attribute.ATTRIBUTE_PASSWORD);
        String mail = request.getParameter(Attribute.ATTRIBUTE_MAIL);

        User user = new User(userName, mail, password, new Date(System.currentTimeMillis()));

        int generatedUserId;
        try {
            generatedUserId = userService.add(user);
            user.setId(generatedUserId);
        } catch (ServiceException e) {
            log.error("Exception occurred in registration command", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        request.getSession().setAttribute(Attribute.ATTRIBUTE_USER, user);

        return "controller?command=goToHome";
    }
}
