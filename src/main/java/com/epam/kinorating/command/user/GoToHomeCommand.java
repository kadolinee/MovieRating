package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToHomeCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        int page = 0;

        if (request.getParameter(Attribute.ATTRIBUTE_PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_PAGE));
        }

        List<Movie> movieList;
        try {
            movieList = MovieService.find(page, null);
        } catch (ServiceException e) {
            log.error("Exception while doing rating ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        request.setAttribute(Attribute.ATTRIBUTE_MOVIES, movieList);

        return PagePath.PAGE_HOME;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
