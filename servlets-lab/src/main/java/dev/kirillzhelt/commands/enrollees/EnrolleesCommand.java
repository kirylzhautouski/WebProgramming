package dev.kirillzhelt.commands.enrollees;

import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.commands.GetCommand;
import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Set;

/**
 * Command to show list of enrollees of getEnrollees set
 */
public abstract class EnrolleesCommand implements GetCommand {

    /**
     * Displays all enrollees by some category for a certain faculty
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        FacultyCommandParams facultyCommandParams = (FacultyCommandParams)params;

        Faculty faculty = facultyCommandParams.getFacultyDao().getFaculty(facultyCommandParams.getFacultyName());
        Set<User> studentNames = this.getEnrollees(facultyCommandParams.getDao(), faculty);
        facultyCommandParams.getRequest().getSession().setAttribute("studentNames", studentNames);

        facultyCommandParams.getRequest().getRequestDispatcher("/views/list_of_enrollees.jsp").forward(facultyCommandParams.getRequest(), facultyCommandParams.getResponse());
    }

    /**
     * Used to retrieve enrollees
     * @param dao
     * @param faculty
     * @return
     */
    protected abstract Set<User> getEnrollees(DaoInterface dao, Faculty faculty);
}
