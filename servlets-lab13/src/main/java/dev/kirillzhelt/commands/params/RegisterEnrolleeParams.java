package dev.kirillzhelt.commands.params;

import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.daos.FacultyDao;
import dev.kirillzhelt.db.models.Application;
import dev.kirillzhelt.db.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Parameters used to register a new enrollee
 */
public class RegisterEnrolleeParams extends CommandParams {

    private final User user;
    private final List<Application> applications;

    public RegisterEnrolleeParams(User user, List<Application> applications,
                                  HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.user = user;
        this.applications = applications;
    }

    /**
     * Construct a newly created params object from POST parameters
     * @param request
     * @param response
     * @return
     */
    public static RegisterEnrolleeParams fromPOSTParams(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("name"), false, request.getParameter("login"), request.getParameter("password"));
        return new RegisterEnrolleeParams(user, null, request, response);
    }

    public User getUser() {
        return user;
    }

    public List<Application> getApplications() {
        return applications;
    }
}
