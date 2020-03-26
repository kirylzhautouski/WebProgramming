package db.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries(
    @NamedQuery(name = "User.login", query = "SELECT u.isAdmin FROM User u WHERE u.login=:login AND u.password=:password")
)
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Application> applications;

    public User() {

    }

    public User(String name, boolean isAdmin, String login, String password) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.login = login;
        this.password = password;
        this.applications = new ArrayList<Application>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", isAdmin=" + isAdmin +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
