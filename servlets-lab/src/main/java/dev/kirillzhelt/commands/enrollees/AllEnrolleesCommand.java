package dev.kirillzhelt.commands.enrollees;

import dev.kirillzhelt.db.daos.interfaces.DaoInterface;
import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import java.util.Set;

/**
 * Command for displaying all enrollees
 */
public class AllEnrolleesCommand extends EnrolleesCommand {

    protected Set<User> getEnrollees(DaoInterface dao, Faculty faculty) {
        return dao.getRegisteredEnrollees(faculty);
    }
}
