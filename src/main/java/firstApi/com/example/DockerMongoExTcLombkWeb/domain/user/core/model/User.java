package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.core.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@EqualsAndHashCode
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public User( String name, String password) {
       // long id,this.id = id;
        this.name = name;
        this.password = password;
    }
    public User() {

    }
    public long getId() {
        return id;
    }
}
