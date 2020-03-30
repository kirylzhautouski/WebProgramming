package dev.kirillzhelt.commands.params;

import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.daos.FacultyDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Parameters for commands used to retrieve enrollees for a certain faculty
 */
public class FacultyCommandParams extends CommandParams {
    private final String facultyName;

    public FacultyCommandParams(String facultyName, HttpServletRequest request, HttpServletResponse response, FacultyDao facultyDao, Dao dao) {
        super(request, response, facultyDao, dao);

        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return this.facultyName;
    }
}
