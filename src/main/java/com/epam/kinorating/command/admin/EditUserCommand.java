package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.command.user.SearchMovieCommand;
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

public class EditUserCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        int id = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_USER_ID));
        double rating = Double.parseDouble(request.getParameter(Attribute.ATTRIBUTE_USER_RATING));

        User user = new User();
        user.setId(id);
        user.setRating(rating);
        try {
            userService.update(user);
        } catch (ServiceException e) {
            log.error("Exception while updating user data", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        return "controller?command=viewUsers";
    }
}
