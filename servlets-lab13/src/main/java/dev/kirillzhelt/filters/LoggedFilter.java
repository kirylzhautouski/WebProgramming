package dev.kirillzhelt.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        boolean isAllowedPageForAnons = request.getRequestURI()
                .equals("/servlets_lab13_war_exploded/UniversityServlet/login-user") ||
            request.getRequestURI()
                .equals("/servlets_lab13_war_exploded/UniversityServlet/register-user");

        if (request.getSession().getAttribute("user") == null && !isAllowedPageForAnons) {
            response.sendRedirect("/servlets_lab13_war_exploded/UniversityServlet/login-user");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
