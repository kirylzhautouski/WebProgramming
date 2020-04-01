package dev.kirillzhelt.db.daos.interfaces;

import dev.kirillzhelt.db.models.Faculty;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface FacultyDaoInterface {

    List<String> getAllFacultyNames();

    Faculty getFaculty(String name);

}
