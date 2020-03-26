package db.daos;

import db.daos.interfaces.FacultyDaoInterface;
import db.models.Faculty;
import db.models.Faculty_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Class that is used to work with database objects Faculty
 */
@Stateless
public class FacultyDao implements FacultyDaoInterface {

    /** Dao's logger */
//    private static final Logger FACULTY_DAO_LOGGER = LogManager.getLogger("dao");

    @PersistenceContext(unitName = "Test_Local")
    private EntityManager manager;

    /**
     * Initializes a newly created Dao object
     */
    public FacultyDao(EntityManagerFactory entityManagerFactory) {
//        FACULTY_DAO_LOGGER.info("FacultyDao is initialized");

        this.manager = entityManagerFactory.createEntityManager();
    }

    public FacultyDao() {

    }

    /**
     * Get names of all faculties
     * @return Faculties names
     */
    public List<String> getAllFacultyNames() {
//        FACULTY_DAO_LOGGER.info("Get all faculty names");

        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.select(facultyRoot.get(Faculty_.name));

        List<String> results = manager.createQuery(query).getResultList();
        return results;
    }

    /**
     * Retrieves Faculty entity by its name
     * @param name Name
     * @return Faculty
     */
    public Faculty getFaculty(String name) {
//        FACULTY_DAO_LOGGER.info("Get faculty");

        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<Faculty> query = cb.createQuery(Faculty.class);
        Root<Faculty> facultyRoot = query.from(Faculty.class);
        query.select(facultyRoot).where(cb.equal(facultyRoot.get(Faculty_.name), name));

        Faculty result = manager.createQuery(query).getSingleResult();
        return result;
    }

}
