package dev.kirillzhelt.db.daos;

import dev.kirillzhelt.db.daos.interfaces.UserDaoInterface;
import dev.kirillzhelt.db.models.User;
import dev.kirillzhelt.db.models.User_;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Class that is used to work with database objects User
 */
@Stateless
public class UserDao implements UserDaoInterface {

    /** Dao's logger */
//    private static final Logger USER_DAO_LOGGER = LogManager.getLogger("dao");

    @PersistenceContext(unitName = "Test_Local")
    private EntityManager manager;

    /**
     * Initializes a newly created Dao object
     */
    public UserDao(EntityManagerFactory entityManagerFactory) {
//        USER_DAO_LOGGER.info("UserDao is initialized");

        this.manager = entityManagerFactory.createEntityManager();
    }

    public UserDao() {

    }

    /**
     * Tries to log user in
     * @param login User's login
     * @param password User's password
     * @return Is user logged in
     */
    public boolean login(String login, String password) {

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
        }
    }
}
