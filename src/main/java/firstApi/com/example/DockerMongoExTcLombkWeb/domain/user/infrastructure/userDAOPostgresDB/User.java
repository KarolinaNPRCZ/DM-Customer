package firstApi.com.example.DockerMongoExTcLombkWeb.domain.user.infrastructure.userDAOPostgresDB;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "userEmail",unique = true)
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;


}
