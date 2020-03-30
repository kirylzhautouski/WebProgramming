package dev.kirillzhelt.servlets;

import dev.kirillzhelt.commands.*;
import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.commands.params.RegisterEnrolleeParams;
import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.daos.FacultyDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    private EntityManagerFactory entityManagerFactory;

    private FacultyDao facultyDao;
    private Dao dao;

    /**
     * Initialize daos
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        entityManagerFactory = Persistence.createEntityManagerFactory("UniversityDB");
        facultyDao = new FacultyDao(entityManagerFactory);
        dao = new Dao(entityManagerFactory);
    }

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
            .replaceFirst("/servlets_lab12_war_exploded/UniversityServlet", "");

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
            .replaceFirst("/servlets_lab12_war_exploded/UniversityServlet", "");

        Command command = CommandManager.getCommandForURL(relativeURL);
        if (command instanceof PostCommand) {
            ((PostCommand) command).executePost(RegisterEnrolleeParams.fromPOSTParams(request, response, facultyDao, dao));
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        entityManagerFactory.close();
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
