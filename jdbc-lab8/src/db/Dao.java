package db;


import db.exceptions.ConnectionPoolException;
import db.exceptions.DaoException;
import db.model.Enrollee;
import db.model.ExamResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that is used to work with database objects
 */
public class Dao {

    private static final Logger DAO_LOGGER = LogManager.getLogger(Dao.class);

    private static final String IS_ADMIN_SQL = "SELECT is_admin FROM users WHERE login = ? AND password = ?";

    private static final String GET_AVERAGE_SQL = "SELECT AVG(a.grade) FROM applications a " +
        "INNER JOIN faculties f ON a.faculty_id = f.id " +
        "WHERE f.name = ?;";

    private static final String GET_REGISTERED_ENROLLEES_SQL = "SELECT DISTINCT u.name " +
        "FROM applications a " +
        "INNER JOIN users u ON a.enrollee_id = u.id " +
        "INNER JOIN faculties f ON a.faculty_id = f.id " +
        "WHERE f.name = ?;";

    private static final String GET_ENROLLEES_ABOVE_AVERAGE_SQL = "SELECT DISTINCT u.name FROM applications o \n" +
        "INNER JOIN users u ON o.enrollee_id = u.id \n" +
        "INNER JOIN faculties f ON o.faculty_id = f.id \n" +
        "WHERE f.name = ? \n" +
        "AND (SELECT AVG(i.grade) FROM applications i WHERE i.enrollee_id = o.enrollee_id) > \n" +
        "(SELECT AVG(a.grade) FROM applications a \n" +
        " INNER JOIN faculties f ON a.faculty_id = f.id \n" +
        " WHERE f.name = ?);";

    private static final String GET_ENROLLEES_SORTED_BY_AVERAGE = "SELECT u.name FROM applications o \n" +
        "INNER JOIN users u ON o.enrollee_id = u.id \n" +
        "INNER JOIN faculties f ON o.faculty_id = f.id \n" +
        "WHERE f.name = ? \n" +
        "GROUP BY u.name\n" +
        "ORDER BY AVG(o.grade) DESC;";

    private static final String INSERT_ENROLLEE_INTO_USERS = "INSERT INTO users(name, is_admin, login, password) " +
        "VALUES(?, FALSE, ?, ?);";

    private static final String INSERT_APPLICATION = "INSERT INTO applications(enrollee_id, faculty_id, subject_id, grade) " +
        "VALUES((SELECT id FROM users WHERE name = ?), (SELECT id FROM faculties WHERE name = ?), " +
        "(SELECT id FROM subjects WHERE name = ?), ?);";

    private static final String GET_FACULTY_PLAN = "SELECT plan FROM faculties WHERE name = ?;";

    private static final String GET_ALL_FACULTIES = "SELECT name FROM faculties;";
    private static final String GET_SUBJECTS_FOR_FACULTY = "SELECT s.name FROM subjects_for_faculties sff " +
        "INNER JOIN subjects s ON s.id = sff.subject_id " +
        "INNER JOIN faculties f ON f.id = sff.faculty_id " +
        "WHERE f.name = ?;";

    private final ConnectionPool connectionPool;

    /**
     * Initializes a newly created Dao object
     */
    public Dao() throws DaoException {
        DAO_LOGGER.info("Dao is initialized");

        try {
            this.connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot use connection pool", e);
        }
    }

    /**
     * Tries to log user in
     * @param login User's login
     * @param password User's password
     * @return Is user logged in
     * @throws DaoException
     */
    public boolean login(String login, String password) throws DaoException {
        DAO_LOGGER.info("Start logging in");

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement isAdminStatement = connection.prepareStatement(IS_ADMIN_SQL);

            isAdminStatement.setString(1, login);
            isAdminStatement.setString(2, password);

            ResultSet resultSet = isAdminStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }

        } catch (SQLException e) {
            DAO_LOGGER.warn("Can't execute prepared statement");
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            DAO_LOGGER.warn("Can't get connection");
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        return false;
    }

    /**
     * Count faculties average grade
     * @param facultyName Name of the faculty
     * @return An average grade
     * @throws DaoException
     */
    public double getAverage(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get average query");

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getAverageStatement = connection.prepareStatement(GET_AVERAGE_SQL);

            getAverageStatement.setString(1, facultyName);

            ResultSet resultSet = getAverageStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        throw new DaoException("No such faculty");
    }

    /**
     * Finds all registered enrollees for the faculty
     * @param facultyName Name of the faculty
     * @return Registered Enrollees
     * @throws DaoException
     */
    public List<String> getRegisteredEnrollees(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get all registered enrollees");

        List<String> result = new ArrayList<>();

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getRegisteredEnrolleesStatement = connection.prepareStatement(GET_REGISTERED_ENROLLEES_SQL);

            getRegisteredEnrolleesStatement.setString(1, facultyName);

            ResultSet resultSet = getRegisteredEnrolleesStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        return result;
    }

    /**
     * Finds enrollees that have grade above averga
     * @param facultyName Faculty to look through
     * @return Enrollees
     * @throws DaoException
     */
    public List<String> getEnrolleesAboveAverage(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get enrollees above average");

        List<String> result = new ArrayList<>();

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getEnrolleesAboveAverageStatement = connection.prepareStatement(GET_ENROLLEES_ABOVE_AVERAGE_SQL);

            getEnrolleesAboveAverageStatement.setString(1, facultyName);
            getEnrolleesAboveAverageStatement.setString(2, facultyName);

            ResultSet resultSet = getEnrolleesAboveAverageStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        return result;
    }

    /**
     * Returns how many enrollees will faculty apply
     * @param facultyName Name of the faculty
     * @return Faculty plan
     * @throws DaoException
     */
    public int getFacultyPlan(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get faculty plan");

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getFacultyPlanStatement = connection.prepareStatement(GET_FACULTY_PLAN);

            getFacultyPlanStatement.setString(1, facultyName);

            ResultSet resultSet = getFacultyPlanStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        throw new DaoException("No such faculty");
    }

    /**
     * Decide enrollees that are applied to the given faculty
     * @param facultyName
     * @return Applied enrollees
     * @throws DaoException
     */
    public List<String> getAppliedEnrollees(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get applied enrollees");

        List<String> result = new ArrayList<>();

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getEnrolleesSortedByAverageStatement = connection.prepareStatement(GET_ENROLLEES_SORTED_BY_AVERAGE);

            getEnrolleesSortedByAverageStatement.setString(1, facultyName);

            ResultSet resultSet = getEnrolleesSortedByAverageStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

            int facultyPlan = this.getFacultyPlan(facultyName);

            return result.stream().limit(facultyPlan).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Register new enrollee to the system
     * @param enrollee Enrollee
     * @throws DaoException
     */
    public void registerEnrollee(Enrollee enrollee) throws DaoException {
        DAO_LOGGER.info("Register enrollee query");

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement insertEnrolleeIntoUsersStatement = connection.prepareStatement(INSERT_ENROLLEE_INTO_USERS);

            insertEnrolleeIntoUsersStatement.setString(1, enrollee.getName());
            insertEnrolleeIntoUsersStatement.setString(2, enrollee.getLogin());
            insertEnrolleeIntoUsersStatement.setString(3, enrollee.getPassword());

            try {
                insertEnrolleeIntoUsersStatement.executeUpdate();
            } catch (PSQLException e) {
                return;
            }

            PreparedStatement insertApplication = connection.prepareStatement(INSERT_APPLICATION);

            for (ExamResult examResult: enrollee.getExams()) {
                insertApplication.setString(1, enrollee.getName());
                insertApplication.setString(2, enrollee.getDesiredFacultyName());
                insertApplication.setString(3, examResult.getSubjectName());
                insertApplication.setInt(4, examResult.getGrade());
                insertApplication.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Get names of all faculties
     * @return Faculties names
     * @throws DaoException
     */
    public List<String> getAllFaculties() throws DaoException {
        DAO_LOGGER.info("Get all faculties query");

        List<String> result = new ArrayList<>();

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getAllFacultiesStatement = connection.prepareStatement(GET_ALL_FACULTIES);

            ResultSet resultSet = getAllFacultiesStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        return result;
    }

    /**
     * Returns subjects that are needed to enroll to the given faculty
     * @param facultyName Faculty name
     * @return Subjects
     * @throws DaoException
     */
    public List<String> getSubjectsForFaculty(String facultyName) throws DaoException {
        DAO_LOGGER.info("Get subjects for faculty");

        List<String> result = new ArrayList<>();

        Connection connection = null;
        try {
            connection = this.connectionPool.getConnection();

            PreparedStatement getSubjectsForFacultyStatement = connection.prepareStatement(GET_SUBJECTS_FOR_FACULTY);

            getSubjectsForFacultyStatement.setString(1, facultyName);

            ResultSet resultSet = getSubjectsForFacultyStatement.executeQuery();
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

        } catch (SQLException e) {
            throw new DaoException("Can't execute prepared statement for faculty subjects", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection", e);
        } finally {
            if (connection != null) {
                this.connectionPool.releaseConnection(connection);
            }
        }

        return result;
    }

}
