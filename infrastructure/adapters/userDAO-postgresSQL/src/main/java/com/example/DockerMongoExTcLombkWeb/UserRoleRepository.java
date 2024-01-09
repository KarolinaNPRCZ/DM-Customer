package com.example.DockerMongoExTcLombkWeb;


import org.springframework.data.jpa.repository.JpaRepository;


interface UserRoleRepository extends JpaRepository<UserRole, Long> {
   UserRole getUserRoleByName(Role name);
}
