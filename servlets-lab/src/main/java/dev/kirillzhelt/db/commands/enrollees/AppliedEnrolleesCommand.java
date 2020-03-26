package dev.kirillzhelt.db.commands.enrollees;

import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import java.util.Set;

/**
 * Command to display all applied enrollees
 */
public class AppliedEnrolleesCommand extends EnrolleesCommand {

    @Override
    protected Set<User> getEnrollees(Dao dao, Faculty faculty) {
        return dao.getAppliedEnrollees(faculty);
    }
}
