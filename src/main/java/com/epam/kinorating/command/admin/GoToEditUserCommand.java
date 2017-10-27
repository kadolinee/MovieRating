package com.epam.kinorating.command.admin;

import com.epam.kinorating.command.Command;
import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.config.PagePath;
import com.epam.kinorating.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToEditUserCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //Do nothing because of I use another version of the overloaded method
    }
}
