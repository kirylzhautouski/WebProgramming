package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.commands.params.RegisterEnrolleeParams;
import dev.kirillzhelt.db.DatabaseStorage;
import dev.kirillzhelt.db.daos.FacultyDao;
import dev.kirillzhelt.db.models.Application;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.Subject;
import dev.kirillzhelt.db.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Complex command that is used to register an enrollee
 */
public class RegisterUserCommand implements GetCommand, PostCommand {

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
        params.getRequest().getRequestDispatcher("/views/register_user.jsp").forward(request, params.getResponse());
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

        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        boolean isAdmin = false;
        if (request.getParameter("is-admin") != null && request.getParameter("is-admin").equals("on")) {
            isAdmin = true;
        }
        User user = new User(name, isAdmin, login, password);
        storage.getDao().registerEnrollee(user);

        params.getResponse().sendRedirect("/servlets_lab13_war_exploded/UniversityServlet");
    }
}
