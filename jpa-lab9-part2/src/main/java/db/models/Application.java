package db.models;

import javax.persistence.*;

@Entity
@Table(name = "application")
@NamedQueries({
    @NamedQuery(name="Application.getRegisteredEnrollees", query="SELECT a.user FROM Application a WHERE a.faculty=:faculty"),
    @NamedQuery(name="Application.getAboveAverage", query="SELECT u FROM Application a JOIN a.user u WHERE a.faculty=:faculty GROUP BY u HAVING AVG(a.grade) > (SELECT AVG(a.grade) FROM Application a WHERE a.faculty=:faculty)"),
    @NamedQuery(name="Application.getSortedByAverage", query="SELECT u FROM Application a JOIN a.user u WHERE a.faculty=:faculty GROUP BY u ORDER BY AVG(a.grade) DESC"),
    @NamedQuery(name="Application.getAverage", query="SELECT AVG(a.grade) FROM Application a WHERE a.faculty=:faculty")
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="faculty_id", nullable=false)
    private Faculty faculty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subject_id", nullable=false)
    private Subject subject;

    @Column(name = "grade")
    private int grade;

    public Application() {

    }

    public Application(User user, Faculty faculty, Subject subject, int grade) {
        this.user = user;
        this.faculty = faculty;
        this.subject = subject;
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Application{" +
            "id=" + id +
            ", user=" + user +
            ", faculty=" + faculty +
            ", subject=" + subject +
            ", grade=" + grade +
            '}';
    }
}
