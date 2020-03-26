package dev.kirillzhelt.db.commands.enrollees;

import dev.kirillzhelt.db.commands.enrollees.EnrolleesCommand;
import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import java.util.Set;

/**
 * Command to display enrollees above average
 */
public class EnrolleesAboveAverageCommand extends EnrolleesCommand {

    @Override
    protected Set<User> getEnrollees(Dao dao, Faculty faculty) {
        return dao.getEnrolleesAboveAverage(faculty);
    }
}
