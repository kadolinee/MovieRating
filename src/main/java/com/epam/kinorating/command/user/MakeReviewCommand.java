package com.epam.kinorating.command.user;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.Messages;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.entity.UserAction;
import com.epam.kinorating.exception.ServiceException;
import com.epam.kinorating.service.UserActionService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class MakeReviewCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Attribute.ATTRIBUTE_USER);

        int movieId = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_MOVIE_ID));
        String review = request.getParameter(Attribute.ATTRIBUTE_REVIEW);

        UserAction userAction = new UserAction(user.getId(), movieId, review, new Date(System.currentTimeMillis()));
        try {
            UserActionService.add(userAction);
        } catch (ServiceException e) {
            log.error("Exception occurred while comment adding ", e);
            request.setAttribute(Attribute.ATTRIBUTE_ERROR_MESSAGE, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        return "controller?command=goToMovie&movieId=" + movieId;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
