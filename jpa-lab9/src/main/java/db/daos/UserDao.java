package db.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

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

        try {
            TypedQuery<Boolean> query = manager.createNamedQuery("User.login", Boolean.class);
            return query.setParameter("login", login).setParameter("password", password).getSingleResult();
        } catch (NoResultException ex) {
            return false;
        } finally {
            manager.close();
        }
    }
}
