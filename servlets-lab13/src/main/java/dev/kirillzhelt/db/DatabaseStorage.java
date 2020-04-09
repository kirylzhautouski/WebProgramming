package dev.kirillzhelt.db;

import dev.kirillzhelt.db.daos.Dao;
import dev.kirillzhelt.db.daos.FacultyDao;
import dev.kirillzhelt.db.daos.UserDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseStorage {

    private static DatabaseStorage instance = new DatabaseStorage();

    private FacultyDao facultyDao;
    private Dao dao;
    private UserDao userDao;

    private DatabaseStorage() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UniversityDB");
        facultyDao = new FacultyDao(entityManagerFactory);
        dao = new Dao(entityManagerFactory);
        userDao = new UserDao(entityManagerFactory);
    }

    public static DatabaseStorage getInstance() {
        return instance;
    }

    public FacultyDao getFacultyDao() {
        return facultyDao;
    }

    public Dao getDao() {
        return dao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
