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
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        String name = request.getParameter(Attribute.ATTRIBUTE_USERNAME);
        String password = request.getParameter(Attribute.ATTRIBUTE_PASSWORD);
        String page;

        User user;
        try {
            user = userService.find(name, password);
        } catch (ServiceException e) {
            log.error("Exception occurred in Login command ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }
        if (user == null) {
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_ENTER, "true");
            page = PagePath.PAGE_LOGIN;
        } else if (user.isBanned()) {
            request.setAttribute(Attribute.ATTRIBUTE_ACCESS_ERROR, "true");
            page = PagePath.PAGE_LOGIN;
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute(Attribute.ATTRIBUTE_USER, user);
            page = "controller?command=goToHome";
        }

        return page;

    }
}
