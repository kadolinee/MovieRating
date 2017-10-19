package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.Movie;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class GoToMovieCommand implements Command{
    private final Logger log = Logger.getLogger(GoToMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        int movieId = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_MOVIE_ID));
        Movie movie = movieService.find(movieId);
        movie.setId(movieId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);

        double movieRating;
        int ratingsNumber;
        ArrayList<UserAction> reviews;
        boolean isRated;
        try {
            Map<String, Object> movieDetails = userActionService.getMovieDetails(movieId);
            movieRating = (Double) movieDetails.get("movieRating");
            ratingsNumber = (Integer) movieDetails.get("ratingsNumber");
            reviews = (ArrayList<UserAction>) movieDetails.get("reviews");
            isRated = false;
            if (user != null) {
                isRated = userActionService.isRated(user.getId(), movieId);
            }
        } catch (ServiceException e) {
            log.error("Exception while doing rating ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        session.setAttribute("isRated", isRated);
        request.setAttribute(Attribute.ATTRIBUTE_MOVIE, movie);
        request.setAttribute(Attribute.ATTRIBUTE_MOVIE_RATING, movieRating);
        request.setAttribute(Attribute.ATTRIBUTE_USER_ACTION_LIST, reviews);
        request.setAttribute(Attribute.ATTRIBUTE_IS_RATED, isRated);
        request.setAttribute(Attribute.ATTRIBUTE_RATINGS_NUMBER, ratingsNumber);

        return PagePath.PAGE_MOVIE;
    }
}
