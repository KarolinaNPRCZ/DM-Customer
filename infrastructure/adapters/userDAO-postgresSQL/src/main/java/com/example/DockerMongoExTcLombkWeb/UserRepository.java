package com.example.DockerMongoExTcLombkWeb;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getUserByUserEmail(String userEmail);
}
