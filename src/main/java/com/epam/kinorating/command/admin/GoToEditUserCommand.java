package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.MovieService;
import com.epam.kinorating.service.UserActionService;
import com.epam.kinorating.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class GoToEditUserCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, UserService userService, MovieService movieService,
                          UserActionService userActionService) {
        int id = Integer.parseInt(request.getParameter(Attribute.ATTRIBUTE_USER_ID));
        String name = request.getParameter(Attribute.ATTRIBUTE_USERNAME);
        double rating = Double.parseDouble(request.getParameter(Attribute.ATTRIBUTE_USER_RATING));

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setRating(rating);

        request.setAttribute(Attribute.ATTRIBUTE_USER, user);

        return PagePath.PAGE_EDIT_USER;
    }
}
