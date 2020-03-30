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
                                  HttpServletRequest request, HttpServletResponse response,
                                  FacultyDao facultyDao, Dao dao) {
        super(request, response, facultyDao, dao);

        this.user = user;
        this.applications = applications;
    }

    /**
     * Construct a newly created params object from POST parameters
     * @param request
     * @param response
     * @param facultyDao
     * @param dao
     * @return
     */
    public static RegisterEnrolleeParams fromPOSTParams(HttpServletRequest request, HttpServletResponse response,
                                                        FacultyDao facultyDao, Dao dao) {
        User user = new User(request.getParameter("name"), false, request.getParameter("login"), request.getParameter("password"));
        return new RegisterEnrolleeParams(user, null, request, response, facultyDao, dao);
    }

    public User getUser() {
        return user;
    }

    public List<Application> getApplications() {
        return applications;
    }
}
