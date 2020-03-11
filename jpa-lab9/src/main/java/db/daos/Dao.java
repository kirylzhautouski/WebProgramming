package db.daos;


import db.models.Faculty;
import db.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that is used to work with database objects using queries with joins
 */
public class Dao {

    /** Dao's logger */
    private static final Logger DAO_LOGGER = LogManager.getLogger("dao");

    /** Factory used to create managers */
    private EntityManagerFactory entityManagerFactory;

    /**
     * Initializes a newly created Dao object
     */
    public Dao(EntityManagerFactory entityManagerFactory) {
        DAO_LOGGER.info("Dao is initialized");

        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Count faculties average grade
     * @param faculty Name of the faculty
     * @return An average grade
     */
    public double getAverage(Faculty faculty) {
        DAO_LOGGER.info("Get average query");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<Double> query = manager.createNamedQuery("Application.getAverage", Double.class);
        double result = query.setParameter("faculty", faculty).getSingleResult();
        manager.close();
        return result;
    }

    /**
     * Finds all registered enrollees for the faculty
     * @param faculty Name of the faculty
     * @return Registered Enrollees
     */
    public Set<User> getRegisteredEnrollees(Faculty faculty) {
        DAO_LOGGER.info("Get all registered enrollees");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("Application.getRegisteredEnrollees", User.class);
        Set<User> result = new HashSet<User>(query.setParameter("faculty", faculty).getResultList());
        manager.close();
        return result;
    }

    /**
     * Finds enrollees that have grade above average
     * @param faculty Faculty to look through
     * @return Enrollees
     */
    public Set<User> getEnrolleesAboveAverage(Faculty faculty) {
        DAO_LOGGER.info("Get enrollees above average");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("Application.getAboveAverage", User.class);
        query.setParameter("faculty", faculty);
        Set<User> result = new HashSet<User>(query.getResultList());
        manager.close();
        return result;
    }

    /**
     * Decide enrollees that are applied to the given faculty
     * @param faculty
     * @return Applied enrollees
     */
    public Set<User> getAppliedEnrollees(Faculty faculty) {
        DAO_LOGGER.info("Get applied enrollees");

        EntityManager manager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery("Application.getSortedByAverage", User.class);
        query.setMaxResults(faculty.getPlan()).setParameter("faculty", faculty);
        Set<User> result = new HashSet<User>(query.getResultList());
        manager.close();
        return result;
    }

    /**
     * Register new enrollee to the system
     * @param enrollee Enrollee
     */
    public void registerEnrollee(User enrollee) {
        DAO_LOGGER.info("Register enrollee query");

        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(enrollee);
        manager.getTransaction().commit();
        manager.close();
    }

}
