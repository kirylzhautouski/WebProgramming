package db.daos;


import db.models.*;
import db.models.Application_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Application> applicationRoot = query.from(Application.class);
        query.select(cb.avg(applicationRoot.get(Application_.grade))).where(cb.equal(applicationRoot.get(Application_.faculty), faculty));

        double result = manager.createQuery(query).getSingleResult();
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
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<Application> applicationRoot = query.from(Application.class);
        query.select(applicationRoot.get(Application_.user)).where(cb.equal(applicationRoot.get(Application_.faculty), faculty));

        Set<User> result = new HashSet<User>(manager.createQuery(query).getResultList());
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

        double average = this.getAverage(faculty);

        EntityManager manager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<User> query = cb.createQuery(User.class);
        Join<Application, User> applicationRoot = query.from(Application.class).join(Application_.user);
        query.select(applicationRoot)
            .where(cb.equal(applicationRoot.getParent().get(Application_.faculty), faculty))
            .groupBy(applicationRoot)
            .having(cb.gt(cb.avg(applicationRoot.getParent().get(Application_.grade)), average));

        Set<User> result = new HashSet<User>(manager.createQuery(query).getResultList());
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
        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<User> query = cb.createQuery(User.class);
        Join<Application, User> applicationRoot = query.from(Application.class).join(Application_.user);
        query.select(applicationRoot)
            .where(cb.equal(applicationRoot.getParent().get(Application_.faculty), faculty))
            .groupBy(applicationRoot)
            .orderBy(cb.desc(cb.avg(applicationRoot.getParent().get(Application_.grade))));

        Set<User> result = new HashSet<User>(manager.createQuery(query).setMaxResults(faculty.getPlan()).getResultList());
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
