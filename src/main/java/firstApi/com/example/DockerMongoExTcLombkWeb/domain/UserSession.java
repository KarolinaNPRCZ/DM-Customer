package firstApi.com.example.DockerMongoExTcLombkWeb.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")

public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private UserSession() {
    }

    public UserSession(String name, String password) {

        this.name = name;
        this.password = password;
    }

}
