package dev.kirillzhelt.db.daos;

import dev.kirillzhelt.db.models.User;
import dev.kirillzhelt.db.models.User_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Class that is used to work with database objects User
 */
public class UserDao {

    /** Dao's logger */
    private static final Logger USER_DAO_LOGGER = LogManager.getLogger("dao");

    /** Factory used to create managers */
    private EntityManagerFactory entityManagerFactory;

    /**
     * Initializes a newly created Dao object
     */
    public UserDao(EntityManagerFactory entityManagerFactory) {
        USER_DAO_LOGGER.info("UserDao is initialized");

        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Tries to log user in
     * @param login User's login
     * @param password User's password
     * @return Is user logged in
     */
    public boolean login(String login, String password) {
        EntityManager manager = entityManagerFactory.createEntityManager();

        CriteriaBuilder cb = manager.getCriteriaBuilder();

        CriteriaQuery<Boolean> isAdminCriteria = cb.createQuery(Boolean.class);
        Root<User> userRoot = isAdminCriteria.from(User.class);
        isAdminCriteria.select(userRoot.get(User_.isAdmin))
            .where(cb.equal(userRoot.get(User_.login), login))
            .where(cb.equal(userRoot.get(User_.password), password));

        try {
            return manager.createQuery(isAdminCriteria).getSingleResult();
        } catch (NoResultException ex) {
            return false;
        } finally {
            manager.close();
        }
    }
}
