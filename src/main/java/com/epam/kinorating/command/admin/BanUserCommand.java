package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.user.SearchMovieCommand;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BanUserCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_USER_ID));

        User user = new User();
        user.setId(id);
        user.setBanned(true);
        try {
            UserService.update(user);
        } catch (ServiceException e) {
            log.error("Exception while banning user data", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        return "controller?command=viewUsers";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
