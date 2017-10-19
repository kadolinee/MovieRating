package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchMovieCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        String name = request.getParameter(Attribute.ATTRIBUTE_SEARCH);

        List<Movie> movies;
        try {
            movies = movieService.find(name);
        } catch (ServiceException e) {
            log.error("Exception while getting film by title ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        request.setAttribute((Attribute.ATTRIBUTE_MOVIES), movies);

        return PagePath.PAGE_FOUND_MOVIES;
    }
}
