package db.daos.interfaces;

import db.models.Faculty;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface FacultyDaoInterface {

    List<String> getAllFacultyNames();

    Faculty getFaculty(String name);

}
