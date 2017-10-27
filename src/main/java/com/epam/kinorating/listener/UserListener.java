package com.epam.kinorating.listener;

import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.entity.User;
import com.epam.kinorating.service.UserService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //Do nothing because of I do not need to track the creation of a session
    }

    public void sessionDestroyed(HttpSessionEvent e) {
        HttpSession session = e.getSession();
        User user = (User) session.getAttribute(Attribute.ATTRIBUTE_USER);

        UserService.update(user);

        session.invalidate();
    }
}
