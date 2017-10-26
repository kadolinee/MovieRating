package com.epam.kinorating.listener;

import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.db.dao.impl.UserDAOImpl;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.UserService;
import com.epam.kinorating.service.impl.*;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {}

    public void sessionDestroyed(HttpSessionEvent e) {
        HttpSession session = e.getSession();
        User user = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);


        UserService userService = new UserService(new UserDAOImpl());
        userService.update(user);

        session.invalidate();
    }
}
