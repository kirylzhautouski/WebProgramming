package db.model;

import java.util.List;

/**
 * An entity class representing an enrollee
 */
public class Enrollee {

    /** Enrollee's name */
    private final String name;

    /** The faculty this enrollee wants to apply to */
    private final String desiredFacultyName;

    /** Exam results */
    private final List<ExamResult> exams;

    /** Enrollee's login */
    private final String login;

    /** Enrollee's password */
    private final String password;

    /**
     *
     * @param name
     *        String
     * @param desiredFacultyName
     *        String
     * @param exams
     *        Exam results
     * @param login
     *        String
     * @param password
     *        String
     */
    public Enrollee(String name, String desiredFacultyName, List<ExamResult> exams, String login, String password) {
        this.name = name;
        this.desiredFacultyName = desiredFacultyName;
        this.exams = exams;
        this.login = login;
        this.password = password;
    }

    /**
     * Getter for name field
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for faculty
     * @return faculty
     */
    public String getDesiredFacultyName() {
        return desiredFacultyName;
    }

    /**
     * Getter for exam results
     * @return exams
     */
    public List<ExamResult> getExams() {
        return exams;
    }

    /**
     * Getter for login results
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for password results
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
