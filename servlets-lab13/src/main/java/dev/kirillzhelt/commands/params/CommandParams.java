package dev.kirillzhelt.commands.params;

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

    public CommandParams(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
