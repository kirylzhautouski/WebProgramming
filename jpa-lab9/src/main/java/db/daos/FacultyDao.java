package db.daos;

import db.models.Faculty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Class that is used to work with database objects Faculty
 */
public class FacultyDao {

    /** Dao's logger */
    private static final Logger FACULTY_DAO_LOGGER = LogManager.getLogger("dao");

    /** Factory used to create managers */
    private EntityManagerFactory entityManagerFactory;

    /**
     * Initializes a newly created Dao object
     */
    public FacultyDao(EntityManagerFactory entityManagerFactory) {
        FACULTY_DAO_LOGGER.info("FacultyDao is initialized");

        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Get names of all faculties
     * @return Faculties names
     */
    public List<Faculty> getAllFaculties() {
        FACULTY_DAO_LOGGER.info("Get all faculties");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<Faculty> facultiesQuery = manager.createNamedQuery("Faculty.findAll", Faculty.class);
        List<Faculty> results = facultiesQuery.getResultList();
        manager.close();
        return results;
    }

    /**
     * Get names of all faculties
     * @return Faculties names
     */
    public List<String> getAllFacultyNames() {
        FACULTY_DAO_LOGGER.info("Get all faculty names");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<String> query = manager.createNamedQuery("Faculty.findAllNames", String.class);
        List<String> results = query.getResultList();
        manager.close();
        return results;
    }

    /**
     * Retrieves Faculty entity by its name
     * @param name Name
     * @return Faculty
     */
    public Faculty getFaculty(String name) {
        FACULTY_DAO_LOGGER.info("Get faculty");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<Faculty> query = manager.createNamedQuery("Faculty.get", Faculty.class);
        Faculty result = query.setParameter("name", name).getSingleResult();
        manager.close();
        return result;
    }

}
