package db.daos;

import db.models.Faculty;
import db.models.Faculty_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<String> getAllFacultyNames() {
        FACULTY_DAO_LOGGER.info("Get all faculty names");

        EntityManager manager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.select(facultyRoot.get(Faculty_.name));

        List<String> results = manager.createQuery(query).getResultList();
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
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<Faculty> query = cb.createQuery(Faculty.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.select(facultyRoot).where(cb.equal(facultyRoot.get(Faculty_.name), name));

        Faculty result = manager.createQuery(query).getSingleResult();
        manager.close();
        return result;
    }

}
