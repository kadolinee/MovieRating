package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class DoRatingCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);

        int movieId = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_MOVIE_ID));
        int ratingSetByUser = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_VALUATION));
        int ratingsNumber = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_RATINGS_NUMBER));
        double movieRating = Double.parseDouble(request.getParameter(Attribute.ATTRIBUTE_MOVIE_RATING));

        UserAction userAction = new UserAction(user.getId(), movieId, ratingSetByUser,
                new Date(System.currentTimeMillis()));

        boolean isRated = (Boolean) session.getAttribute("isRated");
        if (isRated) {
            return PagePath.PAGE_INFO;
        } else {
            try {
                userActionService.add(userAction);
            } catch (ServiceException e) {
                log.error("Exception while doing rating " + e);
                request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
                return PagePath.PAGE_INFO;
            }
            double userRatingChange = userService.calc(ratingsNumber, movieRating, ratingSetByUser);

            user.setRating(user.getRating() + userRatingChange);

            session.setAttribute(Attribute.ATTRIBUTE_USER, user);

            return "controller?command=goToMovie&movieId=" + movieId;
        }

    }
}
