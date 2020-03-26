package db.models;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "Faculty")
@Table(name = "faculty")
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "plan")
    private int plan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjects")
    @JoinTable(
        name = "subjects_for_faculties",
        joinColumns = @JoinColumn(name = "faculty_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "faculty")
    private Set<Application> applications;

    public Faculty() {

    }

    public Faculty(String name, int plan, Set<Subject> subjects) {
        this.name = name;
        this.plan = plan;
        this.subjects = subjects;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlan() {
        return plan;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    @Override
    public String toString() {
        return "Faculty{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", plan=" + plan +
            ", subjects=" + subjects +
            '}';
    }
}
