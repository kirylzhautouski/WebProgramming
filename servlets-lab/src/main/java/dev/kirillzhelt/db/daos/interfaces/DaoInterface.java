package dev.kirillzhelt.db.daos.interfaces;

import dev.kirillzhelt.db.models.Faculty;
import dev.kirillzhelt.db.models.User;

import javax.ejb.Remote;
import java.util.Set;

@Remote
public interface DaoInterface {

    double getAverage(Faculty faculty);

    Set<User> getEnrolleesAboveAverage(Faculty faculty);

    Set<User> getRegisteredEnrollees(Faculty faculty);

    Set<User> getAppliedEnrollees(Faculty faculty);

    void registerEnrollee(User enrollee);
}
