package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Enumerated(EnumType.STRING)
    private Role name;


    @ManyToMany(mappedBy = "roles")
    private List<User> users;



}
