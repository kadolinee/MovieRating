package com.epam.kinorating.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "encodingFilter")
public class EncodingFilter implements Filter {
    public void destroy() {
        //Do nothing because nothing should happen when destroying
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        //Do nothing because nothing should happen when initializing
    }

}
