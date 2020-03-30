package dev.kirillzhelt.commands;

import dev.kirillzhelt.commands.params.CommandParams;
import dev.kirillzhelt.commands.params.FacultyCommandParams;
import dev.kirillzhelt.db.models.Faculty;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Command used to display average grade for a certain faculty
 */
public class AverageGradeCommand implements GetCommand {

    /**
     * Retrieves average grade for a certain faculty and display it
     * @param params
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void executeGet(CommandParams params) throws ServletException, IOException {
        FacultyCommandParams facultyCommandParams = (FacultyCommandParams)params;

        Faculty faculty = facultyCommandParams.getFacultyDao().getFaculty(facultyCommandParams.getFacultyName());
        double averageGrade = facultyCommandParams.getDao().getAverage(faculty);
        facultyCommandParams.getRequest().getSession().setAttribute("averageGrade", averageGrade);
        facultyCommandParams.getRequest().getSession().setAttribute("facultyName", faculty.getName());

        facultyCommandParams.getRequest().getRequestDispatcher("/views/average_grade.jsp").forward(facultyCommandParams.getRequest(), facultyCommandParams.getResponse());
    }
}
