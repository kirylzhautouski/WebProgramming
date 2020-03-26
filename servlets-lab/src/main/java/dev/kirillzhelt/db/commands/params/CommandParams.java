package dev.kirillzhelt.db.commands.params;

import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.daos.FacultyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Generic parameters for commands
 */
public class CommandParams {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final FacultyDao facultyDao;
    private final Dao dao;

    public CommandParams(HttpServletRequest request, HttpServletResponse response, FacultyDao facultyDao, Dao dao) {
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

    public FacultyDao getFacultyDao() {
        return facultyDao;
    }

    public Dao getDao() {
        return dao;
    }
}
