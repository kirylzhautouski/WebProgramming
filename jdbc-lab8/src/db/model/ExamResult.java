package db.model;

/**
 * An entity representing exam result
 */
public class ExamResult {

    /** Subject for an exam */
    private final String subjectName;

    /** Grade for an exam */
    private final int grade;

    /**
     * Constructs ExamResult object
     * @param subjectName String
     * @param grade int
     */
    public ExamResult(String subjectName, int grade) {
        this.subjectName = subjectName;
        this.grade = grade;
    }

    /**
     * Getter for the subject name
     * @return subject
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Getter for the grade
     * @return grade
     */
    public int getGrade() {
        return grade;
    }
}
