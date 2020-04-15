package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.GetCommand;
import dev.kirillzhelt.commands.PostCommand;
import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.db.DatabaseStorage;
import dev.kirillzhelt.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Complex command that is used to register an enrollee
 */
public class LoginUserCommand implements GetCommand, PostCommand {

    private DatabaseStorage storage = DatabaseStorage.getInstance();

    /**
     * Prepares and renders form for adding a new enrollee
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        HttpServletRequest request = params.getRequest();
        params.getRequest().getRequestDispatcher("/views/login_user.jsp").forward(request, params.getResponse());
    }

    /**
     * Adds a new enrollee into db
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executePost(CommandParams params) throws ServletException, IOException {
        HttpServletRequest request = params.getRequest();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = storage.getUserDao().loginUser(login, password);

        request.getSession().setAttribute("user", user);

        params.getResponse().sendRedirect("/servlets_lab13_war_exploded/UniversityServlet");
    }
}
