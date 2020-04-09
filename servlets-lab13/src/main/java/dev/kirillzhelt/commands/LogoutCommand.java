package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command to logout and redirect to main page
 */
public class LogoutCommand implements GetCommand {

    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        params.getRequest().getSession().removeAttribute("user");
        params.getResponse().sendRedirect("/servlets_lab13_war_exploded/UniversityServlet");
    }
}
