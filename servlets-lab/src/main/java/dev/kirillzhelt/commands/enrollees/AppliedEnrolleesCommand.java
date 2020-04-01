package dev.kirillzhelt.commands.enrollees;

import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import java.util.Set;

/**
 * Command to display all applied enrollees
 */
public class AppliedEnrolleesCommand extends EnrolleesCommand {

    @Override
    protected Set<User> getEnrollees(DaoInterface dao, Faculty faculty) {
        return dao.getAppliedEnrollees(faculty);
    }
}
