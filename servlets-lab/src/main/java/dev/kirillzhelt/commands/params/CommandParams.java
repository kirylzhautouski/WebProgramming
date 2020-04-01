package dev.kirillzhelt.commands.params;

import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.daos.interfaces.FacultyDaoInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Generic parameters for commands
 */
public class CommandParams {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final FacultyDaoInterface facultyDao;
    private final DaoInterface dao;

    public CommandParams(HttpServletRequest request, HttpServletResponse response, FacultyDaoInterface facultyDao, DaoInterface dao) {
        this.request = request;
        this.response = response;

        this.facultyDao = facultyDao;
        this.dao = dao;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public FacultyDaoInterface getFacultyDao() {
        return facultyDao;
    }

    public DaoInterface getDao() {
        return dao;
    }
}
