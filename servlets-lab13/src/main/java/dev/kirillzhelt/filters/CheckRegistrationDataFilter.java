package dev.kirillzhelt.filters;

import dev.kirillzhelt.db.models.User;
import dev.kirillzhelt.filters.utils.RegistrationValidation;
import dev.kirillzhelt.filters.utils.RegistrationValidationInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckRegistrationDataFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        if (request.getMethod().equals("POST")) {
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String confirmationPassword = request.getParameter("confirm-password");

            boolean isAdmin = false;
            if (request.getParameter("is-admin") != null && request.getParameter("is-admin").equals("on")) {
                isAdmin = true;
            }

            User user = new User(name, isAdmin, login, password);
            RegistrationValidationInfo validationInfo = RegistrationValidation.validate(user, confirmationPassword);

            if (validationInfo.hasErrorsStatus()) {
                request.getSession().setAttribute("errorMessage", validationInfo.getMessage());
                response.sendRedirect("/servlets_lab13_war_exploded/UniversityServlet/register-user");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
