package db.daos.interfaces;

import db.models.Faculty;
import db.models.User;

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
