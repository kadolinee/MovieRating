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

public class MakeReviewCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        User user = (User) request.getSession().getAttribute(Attribute.ATTRIBUTE_USER);

        int movieId = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_MOVIE_ID));
        String review = request.getParameter(Attribute.ATTRIBUTE_REVIEW);

        UserAction userAction = new UserAction(user.getId(), movieId, review, new Date(System.currentTimeMillis()));
        try {
            userActionService.add(userAction);
        } catch (ServiceException e) {
            log.error("Exception occurred while comment adding ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        return "controller?command=goToMovie&movieId=" + movieId;
    }
}
