package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "userEmail", unique = true)
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userRoles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<UserRole> roles = new ArrayList<>();


    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    protected User() {
    }
}
