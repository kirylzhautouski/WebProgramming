package dev.kirillzhelt.servlets;

import dev.kirillzhelt.commands.*;
import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.commands.params.RegisterEnrolleeParams;
import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.daos.interfaces.FacultyDaoInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * Servlet that handles all requests to the app and passes them to appropriate commands
 */
@WebServlet("/UniversityServlet/*")
public class UniversityServlet extends HttpServlet {

    @EJB
    private FacultyDaoInterface facultyDao;

    @EJB
    private DaoInterface dao;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);

        this.updateUserCookies(request, response);
    }

    /**
     * Pass request to get commands, defaults to main page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String relativeURL = request.getRequestURI()
            .replaceFirst("/servlets-lab12/UniversityServlet", "");

        Command command = CommandManager.getCommandForURL(relativeURL);
        if (command instanceof MainCommand) {
            ((MainCommand) command).executeGet(new CommandParams(request, response, facultyDao, dao));
        } else if (command instanceof GetCommand) {
            FacultyCommandParams facultyCommandParams = new FacultyCommandParams(request.getParameter("facultyName"),
                request, response,
                facultyDao, dao);

            ((GetCommand) command).executeGet(facultyCommandParams);
        }
    }

    /**
     * Pass request to post commands
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String relativeURL = request.getRequestURI()
            .replaceFirst("/servlets-lab12/UniversityServlet", "");

        Command command = CommandManager.getCommandForURL(relativeURL);
        if (command instanceof PostCommand) {
            ((PostCommand) command).executePost(RegisterEnrolleeParams.fromPOSTParams(request, response, facultyDao, dao));
        }
    }

    private void updateUserCookies(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());

        Integer visitCount = 0;
        String visitCountKey = "visitCount";
        String userIDKey = "userID";
        String userID = "QWER";

        if (session.isNew()) {
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer)session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID = (String)session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey,  visitCount);

        Cookie cookie = new Cookie("cookie", visitCount + session.getLastAccessedTime() + "");
        response.addCookie(cookie);
    }
}
