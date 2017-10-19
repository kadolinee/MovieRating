package com.epam.kinorating.filter;

import com.epam.kinorating.config.Attribute;
import com.epam.kinorating.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebFilter(filterName = "accessFilter")
public class AccessFilter implements Filter {
    private List<String> illegalForGuest = new ArrayList<>();
    private List<String> illegalForUser = new ArrayList<>();
    private List<String> illegalForAdmin = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        illegalForGuest = asList(filterConfig.getInitParameter("illegalForGuest"));
        illegalForUser = asList(filterConfig.getInitParameter("illegalForUser"));
        illegalForAdmin = asList(filterConfig.getInitParameter("illegalForAdmin"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String commandName = req.getParameter("command");

        User user = (User) req.getSession().getAttribute(Attribute.ATTRIBUTE_USER);

        if (user == null && illegalForGuest.contains(commandName)) {
            resp.sendRedirect("controller?command=goToError");
        }
        if (user != null) {
            if (user.getRoleId() == 2 && illegalForUser.contains(commandName)) {
                resp.sendRedirect("controller?command=goToError");
            } else if (user.getRoleId() == 1 && illegalForAdmin.contains(commandName)){
                resp.sendRedirect("controller?command=goToError");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private List<String> asList(String str) {
        String[] array = str.split(" ");

        List<String> list = new ArrayList<>();
        Collections.addAll(list, array);

        return list;
    }

    @Override
    public void destroy() {

    }
}
