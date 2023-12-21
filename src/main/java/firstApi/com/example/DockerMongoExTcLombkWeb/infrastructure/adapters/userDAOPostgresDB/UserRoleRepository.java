package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userDAOPostgresDB;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 interface UserRoleRepository extends JpaRepository<UserRole, Long> {
   // UserRole getUserRoleByName(Role name);
}
