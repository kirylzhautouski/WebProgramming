package dev.kirillzhelt.commands.params;

import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.daos.interfaces.FacultyDaoInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Parameters for commands used to retrieve enrollees for a certain faculty
 */
public class FacultyCommandParams extends CommandParams {
    private final String facultyName;

    public FacultyCommandParams(String facultyName, HttpServletRequest request, HttpServletResponse response, FacultyDaoInterface facultyDao, DaoInterface dao) {
        super(request, response, facultyDao, dao);

        this.facultyName = facultyName;
    }

    public String getFacultyName() {
        return this.facultyName;
    }
}
