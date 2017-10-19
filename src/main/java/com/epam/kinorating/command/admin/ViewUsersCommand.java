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
import java.util.List;

public class ViewUsersCommand implements Command{
    private final Logger log = Logger.getLogger(SearchMovieCommand.class);

    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        List<User> userList;
        try {
            userList = userService.findAll();
        } catch(ServiceException e) {
            log.error("Exception while getting film by title ", e);
            request.setAttribute(Attribute.ATTRIBUTE_USER, Messages.UNKNOWN_ERROR);
            return PagePath.PAGE_INFO;
        }

        request.setAttribute(Attribute.ATTRIBUTE_USER_LIST, userList);

        return PagePath.PAGE_VIEW_USERS;
    }
}
