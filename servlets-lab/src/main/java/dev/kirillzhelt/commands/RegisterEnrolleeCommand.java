package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.commands.params.RegisterEnrolleeParams;
import dev.kirillzhelt.db.daos.interfaces.FacultyDaoInterface;
import dev.kirillzhelt.db.models.Application;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.Subject;
import dev.kirillzhelt.db.models.User;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Complex command that is used to register an enrollee
 */
public class RegisterEnrolleeCommand implements GetCommand, PostCommand {
    /**
     * Prepares and renders form for adding a new enrollee
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        FacultyCommandParams facultyCommandParams = (FacultyCommandParams)params;

        Faculty faculty = facultyCommandParams.getFacultyDao().getFaculty(facultyCommandParams.getFacultyName());

        HttpSession session = facultyCommandParams.getRequest().getSession();
        session.setAttribute("facultyName", faculty.getName());
        session.setAttribute("subjects", faculty.getSubjects());

        facultyCommandParams.getRequest().getRequestDispatcher("/views/register_enrollee.jsp").forward(facultyCommandParams.getRequest(), facultyCommandParams.getResponse());
    }

    /**
     * Adds a new enrollee into db
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executePost(CommandParams params) throws ServletException, IOException {
        RegisterEnrolleeParams registerEnrolleeParams = (RegisterEnrolleeParams)params;
        HttpServletRequest request = registerEnrolleeParams.getRequest();

        User user = registerEnrolleeParams.getUser();

        FacultyDaoInterface facultyDao = registerEnrolleeParams.getFacultyDao();
        Faculty faculty = facultyDao.getFaculty(request.getParameter("facultyName"));

        for (Subject subject : faculty.getSubjects()) {
            user.addApplication(new Application(user, faculty, subject, Integer.parseInt(request.getParameter(subject.getName()))));
        }

        try {
            registerEnrolleeParams.getDao().registerEnrollee(user);
        } catch (EJBException ex) {
            request.getSession().setAttribute("errorMessage", ex.getMessage());
            registerEnrolleeParams.getRequest().getRequestDispatcher("/views/error_page.jsp").forward(registerEnrolleeParams.getRequest(), registerEnrolleeParams.getResponse());
        }

        registerEnrolleeParams.getResponse().sendRedirect("/servlets-lab12/UniversityServlet");
    }
}
